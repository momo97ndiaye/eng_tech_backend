package com.engtechnologie.microcredit.reference.division;

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
class DivisionControllerTest {

    @Mock
    private DivisionService service;

    @InjectMocks
    private DivisionController controller;


    @Test
    void given_ADivisionDto_When_CreateIsCalled_Then_ADivisionIsCreatedAndItsPathReturnedWithCreatedStatus() {

        // Arrange
        var divisionEntity = DivisionEntity.builder().id(510).build();
        var divisionDto = DivisionDto.builder().build();
        given(service.create(divisionDto)).willReturn(divisionEntity);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        // Act
        var result = controller.create(divisionDto);

        // Assert
        assertEquals(CREATED, result.getStatusCode());
        assertEquals(URI.create("http://localhost/510"), result.getHeaders().getLocation());
    }

    @Test
    void given_ACollectionOfDivisionsExist_When_GetAllIsCalled_Then_ThoseDivisionsAreReturned() {

        // Arrange
        var divisions = singleton(DivisionDto.builder().build());
        given(service.getAll()).willReturn(divisions);

        // Act
        var result = controller.getAll();

        // Assert
        assertEquals(divisions, result);
    }

    @Test
    void given_ADivisionExistsById_When_GetByIdIsCalled_Then_ThatDivisionIsReturned() {

        // Arrange
        var division = DivisionDto.builder().build();
        given(service.getById(112)).willReturn(division);

        // Act
        var result = controller.getById(112);

        // Assert
        assertEquals(division, result);
    }

    @Test
    void given_ADivisionExistsById_When_UpdateIsCalledWithADivisionDto_Then_TheDivisionIsUpdatedAndNoContentStatusReturned() {

        // Arrange
        var divisionDto = DivisionDto.builder().build();

        // Act
        var result = controller.update(149, divisionDto);

        // Assert
        assertEquals(NO_CONTENT, result.getStatusCode());

        then(service).should().update(149, divisionDto);
    }

    @Test
    void given_ADivisionExistsById_When_DeleteByIdIsCalled_Then_ThatDivisionIsDeletedAndNoContentStatusReturned() {

        // Arrange
        var divisionId = 120;

        // Act
        var result = controller.deleteById(divisionId);

        // Assert
        assertEquals(NO_CONTENT, result.getStatusCode());

        then(service).should().deleteById(divisionId);
    }
}