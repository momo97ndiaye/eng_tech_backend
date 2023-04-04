package com.engtechnologie.microcredit.reference.interestrate;

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
class InterestRateControllerTest {

    @Mock
    private InterestRateService service;

    @InjectMocks
    private InterestRateController controller;


    @Test
    void given_AnInterestRateDto_When_CreateIsCalled_Then_AnInterestRateIsCreatedAndItsPathReturnedWithCreatedStatus() {

        // Arrange
        var interestRateEntity = InterestRateEntity.builder().id(68).build();
        var interestRateDto = InterestRateDto.builder().build();
        given(service.create(interestRateDto)).willReturn(interestRateEntity);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        // Act
        var result = controller.create(interestRateDto);

        // Assert
        assertEquals(CREATED, result.getStatusCode());
        assertEquals(URI.create("http://localhost/68"), result.getHeaders().getLocation());
    }

    @Test
    void given_ACollectionOfInterestRatesExist_When_GetAllIsCalled_Then_ThoseInterestRatesAreReturned() {

        // Arrange
        var interestRates = singleton(InterestRateDto.builder().build());
        given(service.getAll()).willReturn(interestRates);

        // Act
        var result = controller.getAll();

        // Assert
        assertEquals(interestRates, result);
    }

    @Test
    void given_AnInterestRateExistsById_When_GetByIdIsCalled_Then_ThatInterestRateIsReturned() {

        // Arrange
        var interestRat = InterestRateDto.builder().build();
        given(service.getById(19)).willReturn(interestRat);

        // Act
        var result = controller.getById(19);

        // Assert
        assertEquals(interestRat, result);
    }

    @Test
    void given_AnInterestRateExistsById_When_UpdateIsCalledWithAnInterestRateDto_Then_TheInterestRateIsUpdatedAndNoContentStatusReturned() {

        // Arrange
        var interestRateDto = InterestRateDto.builder().build();

        // Act
        var result = controller.update(29, interestRateDto);

        // Assert
        assertEquals(NO_CONTENT, result.getStatusCode());

        then(service).should().update(29, interestRateDto);
    }

    @Test
    void given_AnInterestRateExistsById_When_DeleteByIdIsCalled_Then_ThatInterestRateIsDeletedAndNoContentStatusReturned() {

        // Arrange
        var interestRateId = 76;

        // Act
        var result = controller.deleteById(interestRateId);

        // Assert
        assertEquals(NO_CONTENT, result.getStatusCode());

        then(service).should().deleteById(interestRateId);
    }
}