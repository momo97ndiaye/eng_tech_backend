package com.engtechnologie.microcredit.reference.repaymentmethod;

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
class RepaymentMethodControllerTest {

    @Mock
    private RepaymentMethodService service;

    @InjectMocks
    private RepaymentMethodController controller;


    @Test
    void given_ARepaymentMethodDto_When_CreateIsCalled_Then_ARepaymentMethodIsCreatedAndItsPathReturnedWithCreatedStatus() {

        // Arrange
        var repaymentMethodEntity = RepaymentMethodEntity.builder().id(512).build();
        var repaymentMethodDto = RepaymentMethodDto.builder().build();
        given(service.create(repaymentMethodDto)).willReturn(repaymentMethodEntity);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        // Act
        var result = controller.create(repaymentMethodDto);

        // Assert
        assertEquals(CREATED, result.getStatusCode());
        assertEquals(URI.create("http://localhost/512"), result.getHeaders().getLocation());
    }

    @Test
    void given_ACollectionOfRepaymentMethodsExist_When_GetAllIsCalled_Then_ThoseRepaymentMethodsAreReturned() {

        // Arrange
        var repaymentMethods = singleton(RepaymentMethodDto.builder().build());
        given(service.getAll()).willReturn(repaymentMethods);

        // Act
        var result = controller.getAll();

        // Assert
        assertEquals(repaymentMethods, result);
    }

    @Test
    void given_ARepaymentMethodExistsById_When_GetByIdIsCalled_Then_ThatRepaymentMethodIsReturned() {

        // Arrange
        var repaymentMethod = RepaymentMethodDto.builder().build();
        given(service.getById(423)).willReturn(repaymentMethod);

        // Act
        var result = controller.getById(423);

        // Assert
        assertEquals(repaymentMethod, result);
    }

    @Test
    void given_ARepaymentMethodExistsById_When_UpdateIsCalledWithARepaymentMethodDto_Then_TheRepaymentMethodIsUpdatedAndNoContentStatusReturned() {

        // Arrange
        var repaymentMethodDto = RepaymentMethodDto.builder().build();

        // Act
        var result = controller.update(356, repaymentMethodDto);

        // Assert
        assertEquals(NO_CONTENT, result.getStatusCode());

        then(service).should().update(356, repaymentMethodDto);
    }

    @Test
    void given_ARepaymentMethodExistsById_When_DeleteByIdIsCalled_Then_ThatRepaymentMethodIsDeletedAndNoContentStatusReturned() {

        // Arrange
        var repaymentMethodId = 178;

        // Act
        var result = controller.deleteById(repaymentMethodId);

        // Assert
        assertEquals(NO_CONTENT, result.getStatusCode());

        then(service).should().deleteById(repaymentMethodId);
    }
}