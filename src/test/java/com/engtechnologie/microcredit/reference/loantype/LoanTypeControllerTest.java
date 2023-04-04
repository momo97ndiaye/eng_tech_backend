package com.engtechnologie.microcredit.reference.loantype;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URI;

import static java.util.Collections.singleton;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@ExtendWith(MockitoExtension.class)
class LoanTypeControllerTest {

    @Mock
    private LoanTypeService service;

    @InjectMocks
    private LoanTypeController controller;


    @Test
    void given_ALoanTypeDto_When_CreateIsCalled_Then_ALoanTypeIsCreatedAndItsPathReturnedWithCreatedStatus() {

        // Arrange
        var loanTypeEntity = LoanTypeEntity.builder().id(109).build();
        var loanTypeDto = LoanTypeDto.builder().build();
        given(service.create(loanTypeDto)).willReturn(loanTypeEntity);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        // Act
        var result = controller.create(loanTypeDto);

        // Assert
        assertEquals(CREATED, result.getStatusCode());
        assertEquals(URI.create("http://localhost/109"), result.getHeaders().getLocation());
    }

    @Test
    void given_ACollectionOfLoanTypesExist_When_GetAllIsCalled_Then_ThoseLoanTypesAreReturned() {

        // Arrange
        var loanTypes = singleton(LoanTypeDto.builder().build());
        given(service.getAll()).willReturn(loanTypes);

        // Act
        var result = controller.getAll();

        // Assert
        assertEquals(loanTypes, result);
    }

    @Test
    void given_ALoanTypeExistsById_When_GetByIdIsCalled_Then_ThatLoanTypeIsReturned() {

        // Arrange
        var loanType = LoanTypeDto.builder().build();
        given(service.getById(65)).willReturn(loanType);

        // Act
        var result = controller.getById(65);

        // Assert
        assertEquals(loanType, result);
    }

    @Test
    void given_ALoanTypeExistsById_When_UpdateIsCalledWithALoanTypeDto_Then_TheLoanTypeIsUpdatedAndNoContentStatusReturned() {

        // Arrange
        var loanTypeDto = LoanTypeDto.builder().build();

        // Act
        var result = controller.update(48, loanTypeDto);

        // Assert
        assertEquals(NO_CONTENT, result.getStatusCode());

        then(service).should().update(48, loanTypeDto);
    }

    @Test
    void given_ALoanTypeExistsById_When_DeleteByIdIsCalled_Then_ThatLoanTypeIsDeletedAndNoContentStatusReturned() {

        // Arrange
        var loanTypeId = 47;

        // Act
        var result = controller.deleteById(loanTypeId);

        // Assert
        assertEquals(NO_CONTENT, result.getStatusCode());

        then(service).should().deleteById(loanTypeId);
    }
}