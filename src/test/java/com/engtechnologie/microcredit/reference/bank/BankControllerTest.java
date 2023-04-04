package com.engtechnologie.microcredit.reference.bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
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
class BankControllerTest {

    @Mock
    private BankService service;

    @Autowired
    private BankMapper mapper;

    @InjectMocks
    private BankController controller;


    @Test
    void given_ABankDto_When_CreateIsCalled_Then_ABankIsCreatedAndItsPathReturnedWithCreatedStatus() {

        // Arrange
        var bankResponse = BankDto.builder().id(1).build();
        var bankDto = BankDto.builder().build();
        given(service.create(bankDto)).willReturn(bankResponse);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        // Act
        var result = controller.create(bankDto);

        // Assert
        assertEquals(CREATED, result.getStatusCode());
        assertEquals(URI.create("http://localhost/1"), result.getHeaders().getLocation());
    }

    @Test
    void given_ACollectionOfBanksExist_When_GetAllIsCalled_Then_ThoseBanksAreReturned() {

        // Arrange
        var banks = singleton(BankDto.builder().build());
        given(service.getAll()).willReturn(banks);

        // Act
        var result = controller.getAll();

        // Assert
        assertEquals(banks, result);
    }

    @Test
    void given_ABankExistsById_When_GetByIdIsCalled_Then_ThatBankIsReturned() {

        // Arrange
        var bank = BankDto.builder().build();
        given(service.getById(12)).willReturn(bank);

        // Act
        var result = controller.getById(12);

        // Assert
        assertEquals(bank, result);
    }

    @Test
    void given_ABankExistsById_When_UpdateIsCalledWithABankDto_Then_TheBankIsUpdatedAndNoContentStatusReturned() {

        // Arrange
        var bankDto = BankDto.builder().build();

        // Act
        var result = controller.update(11, bankDto);

        // Assert
        assertEquals(NO_CONTENT, result.getStatusCode());

        then(service).should().update(11, bankDto);
    }

    @Test
    void given_ABankExistsById_When_DeleteByIdIsCalled_Then_ThatBankIsDeletedAndNoContentStatusReturned() {

        // Arrange
        var bankId = 7;

        // Act
        var result = controller.deleteById(bankId);

        // Assert
        assertEquals(NO_CONTENT, result.getStatusCode());

        then(service).should().deleteById(bankId);
    }
}