package com.engtechnologie.microcredit.reference.lender;

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
class LenderControllerTest {

    @Mock
    private LenderService service;

    @InjectMocks
    private LenderController controller;


    @Test
    void given_ALenderDto_When_CreateIsCalled_Then_ALenderIsCreatedAndItsPathReturnedWithCreatedStatus() {

        // Arrange
        var lenderEntity = LenderEntity.builder().id(109).build();
        var lenderDto = LenderDto.builder().build();
        given(service.create(lenderDto)).willReturn(lenderEntity);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        // Act
        var result = controller.create(lenderDto);

        // Assert
        assertEquals(CREATED, result.getStatusCode());
        assertEquals(URI.create("http://localhost/109"), result.getHeaders().getLocation());
    }

    @Test
    void given_ACollectionOfLendersExist_When_GetAllIsCalled_Then_ThoseLendersAreReturned() {

        // Arrange
        var lenders = singleton(LenderDto.builder().build());
        given(service.getAll()).willReturn(lenders);

        // Act
        var result = controller.getAll();

        // Assert
        assertEquals(lenders, result);
    }

    @Test
    void given_ALenderExistsById_When_GetByIdIsCalled_Then_ThatLenderIsReturned() {

        // Arrange
        var lender = LenderDto.builder().build();
        given(service.getById(46)).willReturn(lender);

        // Act
        var result = controller.getById(46);

        // Assert
        assertEquals(lender, result);
    }

    @Test
    void given_ALenderExistsById_When_UpdateIsCalledWithALenderDto_Then_TheLenderIsUpdatedAndNoContentStatusReturned() {

        // Arrange
        var lenderDto = LenderDto.builder().build();

        // Act
        var result = controller.update(52, lenderDto);

        // Assert
        assertEquals(NO_CONTENT, result.getStatusCode());

        then(service).should().update(52, lenderDto);
    }

    @Test
    void given_ALenderExistsById_When_DeleteByIdIsCalled_Then_ThatLenderIsDeletedAndNoContentStatusReturned() {

        // Arrange
        var lenderId = 94;

        // Act
        var result = controller.deleteById(lenderId);

        // Assert
        assertEquals(NO_CONTENT, result.getStatusCode());

        then(service).should().deleteById(lenderId);
    }
}