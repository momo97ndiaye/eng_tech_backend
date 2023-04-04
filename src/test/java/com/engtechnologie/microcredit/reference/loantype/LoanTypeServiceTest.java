package com.engtechnologie.microcredit.reference.loantype;

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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class LoanTypeServiceTest {

    @Mock
    private LoanTypeMapper mapper;

    @Mock
    private LoanTypeRepository repository;

    @InjectMocks
    private LoanTypeService service;


    @Test
    void given_ALoanTypeDto_When_CreateIsCalled_Then_ALoanTypeIsCreatedAndReturned() {

        // Arrange
        var loanTypeDto = LoanTypeDto.builder().build();
        var loanTypeEntity = LoanTypeEntity.builder().build();

        given(mapper.toEntity(loanTypeDto)).willReturn(loanTypeEntity);
        given(repository.save(loanTypeEntity)).willReturn(loanTypeEntity);

        // Act
        var result = service.create(loanTypeDto);

        // Assert
        assertEquals(loanTypeEntity, result);
    }

    @Test
    void given_ACollectionOfLoanTypesExist_When_GetAllIsCalled_Then_ThoseLoanTypesAreReturned() {

        // Arrange
        var loanTypeEntities = singletonList(new LoanTypeEntity());
        given(repository.findAll()).willReturn(loanTypeEntities);

        var loanTypes = singleton(LoanTypeDto.builder().build());
        given(mapper.toDto(loanTypeEntities)).willReturn(loanTypes);


        // Act
        var result = service.getAll();

        // Assert
        assertEquals(loanTypes, result);
    }

    @Test
    void given_ALoanTypeExistsById_When_GetByIdIsCalled_Then_ThatLoanTypeIsReturned() {

        // Arrange
        var loanTypeEntity = new LoanTypeEntity();
        given(repository.findById(141)).willReturn(Optional.of(loanTypeEntity));

        var loanType = LoanTypeDto.builder().build();
        given(mapper.toDto(loanTypeEntity)).willReturn(loanType);

        // Act
        var result = service.getById(141);

        // Assert
        assertEquals(loanType, result);
    }

    @Test
    void given_ALoanTypeExistsById_When_UpdateIsCalledWithALoanTypeDto_Then_TheLoanTypeIsUpdated() {

        // Arrange
        var loanTypeEntity = new LoanTypeEntity();
        given(repository.findById(107)).willReturn(Optional.of(loanTypeEntity));

        given(repository.save(loanTypeEntity)).willReturn(loanTypeEntity);

        var loanTypeDto = LoanTypeDto.builder().build();

        // Act
        var result = service.update(107, loanTypeDto);

        // Assert
        assertEquals(loanTypeEntity, result);

        then(mapper).should().updateEntity(loanTypeDto, loanTypeEntity);
    }

    @Test
    void given_ALoanTypeExistsById_When_DeleteByIdIsCalled_Then_ThatLoanTypeIsDeleted() {

        // Arrange
        var loanTypeEntity = new LoanTypeEntity();
        given(repository.findById(210)).willReturn(Optional.of(loanTypeEntity));

        given(repository.save(loanTypeEntity)).willReturn(loanTypeEntity);

        // Act
        var result = service.deleteById(210);

        // Assert
        assertEquals(loanTypeEntity, result);
        assertEquals(INACTIVE, loanTypeEntity.getStatus());
    }
}