package com.engtechnologie.microcredit.reference.bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.engtechnologie.microcredit.enumeration.ReferenceStatus.INACTIVE;
import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class BankServiceTest {

    @Mock
    private BankMapper mapper;

    @Mock
    private BankRepository repository;

    @InjectMocks
    private BankService service;


    @Test
    void given_ABankDto_When_CreateIsCalled_Then_ABankIsCreatedAndReturned() {

        // Arrange
        var bankDto = BankDto.builder().build();
        var bankEntity = BankEntity.builder().build();

        given(mapper.toEntity(bankDto)).willReturn(bankEntity);
        given(repository.save(bankEntity)).willReturn(bankEntity);

        // Act
        var result = service.create(bankDto);

        // Assert
        assertNotNull(bankEntity);
    }

    @Test
    void given_ACollectionOfBanksExist_When_GetAllIsCalled_Then_ThoseBanksAreReturned() {

        // Arrange
        var bankEntities = singletonList(new BankEntity());
        given(repository.findAll()).willReturn(bankEntities);

        var banks = singleton(BankDto.builder().build());
        given(mapper.toDto(bankEntities)).willReturn(banks);


        // Act
        var result = service.getAll();

        // Assert
        assertEquals(banks, result);
    }

    @Test
    void given_ABankExistsById_When_GetByIdIsCalled_Then_ThatBankIsReturned() {

        // Arrange
        var bankEntity = new BankEntity();
        given(repository.findById(17)).willReturn(Optional.of(bankEntity));

        var bank = BankDto.builder().build();
        given(mapper.toDto(bankEntity)).willReturn(bank);

        // Act
        var result = service.getById(17);

        // Assert
        assertEquals(bank, result);
    }

    @Test
    void given_ABankExistsById_When_UpdateIsCalledWithABankDto_Then_TheBankIsUpdated() {

        // Arrange
        var bankEntity = new BankEntity();
        var bankDto = BankDto.builder().id(93).name("Eng").build();

        bankEntity.setId(93);
        given(repository.findById(93)).willReturn(Optional.of(bankEntity));

        given(repository.save(bankEntity)).willReturn(bankEntity);

        given(mapper.toDto(bankEntity)).willReturn(bankDto);

        // Act
        var result = service.update(93, bankDto);

        // Assert
        assertEquals(bankEntity.getId(), result.id());

        then(mapper).should().updateEntity(bankDto, bankEntity);
    }

    @Test
    void given_ABankExistsById_When_DeleteByIdIsCalled_Then_ThatBankIsDeleted() {

        // Arrange
        var bankEntity = new BankEntity();
        given(repository.findById(23)).willReturn(Optional.of(bankEntity));

        given(repository.save(bankEntity)).willReturn(bankEntity);

        // Act
        var result = service.deleteById(23);

        // Assert
        assertEquals(bankEntity, result);
        assertEquals(INACTIVE, bankEntity.getStatus());
    }
}