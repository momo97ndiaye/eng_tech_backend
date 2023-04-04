package com.engtechnologie.microcredit.reference.loanapplicationtype;

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
class LoanApplicationTypeControllerTest {

    @Mock
    private LoanApplicationTypeService service;

    @InjectMocks
    private LoanApplicationTypeController controller;


    @Test
    void given_ALoanApplicationTypeDto_When_CreateIsCalled_Then_ALoanApplicationTypeIsCreatedAndItsPathReturnedWithCreatedStatus() {

        // Arrange
        var loanApplicationTypeEntity = LoanApplicationTypeEntity.builder().id(87).build();
        var loanApplicationTypeDto = LoanApplicationTypeDto.builder().build();
        given(service.create(loanApplicationTypeDto)).willReturn(loanApplicationTypeEntity);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        // Act
        var result = controller.create(loanApplicationTypeDto);

        // Assert
        assertEquals(CREATED, result.getStatusCode());
        assertEquals(URI.create("http://localhost/87"), result.getHeaders().getLocation());
    }

    @Test
    void given_ACollectionOfLoanApplicationTypesExist_When_GetAllIsCalled_Then_ThoseLoanApplicationTypesAreReturned() {

        // Arrange
        var loanApplicationTypes = singleton(LoanApplicationTypeDto.builder().build());
        given(service.getAll()).willReturn(loanApplicationTypes);

        // Act
        var result = controller.getAll();

        // Assert
        assertEquals(loanApplicationTypes, result);
    }

    @Test
    void given_ALoanApplicationTypeExistsById_When_GetByIdIsCalled_Then_ThatLoanApplicationTypeIsReturned() {

        // Arrange
        var loanApplicationType = LoanApplicationTypeDto.builder().build();
        given(service.getById(29)).willReturn(loanApplicationType);

        // Act
        var result = controller.getById(29);

        // Assert
        assertEquals(loanApplicationType, result);
    }

    @Test
    void given_ALoanApplicationTypeExistsById_When_UpdateIsCalledWithALoanApplicationTypeDto_Then_TheLoanApplicationTypeIsUpdatedAndNoContentStatusReturned() {

        // Arrange
        var loanApplicationTypeDto = LoanApplicationTypeDto.builder().build();

        // Act
        var result = controller.update(8, loanApplicationTypeDto);

        // Assert
        assertEquals(NO_CONTENT, result.getStatusCode());

        then(service).should().update(8, loanApplicationTypeDto);
    }

    @Test
    void given_ALoanApplicationTypeExistsById_When_DeleteByIdIsCalled_Then_ThatLoanApplicationTypeIsDeletedAndNoContentStatusReturned() {

        // Arrange
        var loanApplicationTypeId = 22;

        // Act
        var result = controller.deleteById(loanApplicationTypeId);

        // Assert
        assertEquals(NO_CONTENT, result.getStatusCode());

        then(service).should().deleteById(loanApplicationTypeId);
    }
}