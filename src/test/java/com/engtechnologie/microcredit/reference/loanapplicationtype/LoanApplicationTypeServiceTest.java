package com.engtechnologie.microcredit.reference.loanapplicationtype;

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
class LoanApplicationTypeServiceTest {

    @Mock
    private LoanApplicationTypeMapper mapper;

    @Mock
    private LoanApplicationTypeRepository repository;

    @InjectMocks
    private LoanApplicationTypeService service;


    @Test
    void given_ALoanApplicationTypeDto_When_CreateIsCalled_Then_ALoanApplicationTypeIsCreatedAndReturned() {

        // Arrange
        var loanApplicationTypeDto = LoanApplicationTypeDto.builder().build();
        var loanApplicationTypeEntity = LoanApplicationTypeEntity.builder().build();

        given(mapper.toEntity(loanApplicationTypeDto)).willReturn(loanApplicationTypeEntity);
        given(repository.save(loanApplicationTypeEntity)).willReturn(loanApplicationTypeEntity);

        // Act
        var result = service.create(loanApplicationTypeDto);

        // Assert
        assertEquals(loanApplicationTypeEntity, result);
    }

    @Test
    void given_ACollectionOfLoanApplicationTypesExist_When_GetAllIsCalled_Then_ThoseLoanApplicationTypesAreReturned() {

        // Arrange
        var loanApplicationTypeEntities = singletonList(new LoanApplicationTypeEntity());
        given(repository.findAll()).willReturn(loanApplicationTypeEntities);

        var loanApplicationTypes = singleton(LoanApplicationTypeDto.builder().build());
        given(mapper.toDto(loanApplicationTypeEntities)).willReturn(loanApplicationTypes);


        // Act
        var result = service.getAll();

        // Assert
        assertEquals(loanApplicationTypes, result);
    }

    @Test
    void given_ALoanApplicationTypeExistsById_When_GetByIdIsCalled_Then_ThatLoanApplicationTypeIsReturned() {

        // Arrange
        var loanApplicationTypeEntity = new LoanApplicationTypeEntity();
        given(repository.findById(37)).willReturn(Optional.of(loanApplicationTypeEntity));

        var loanApplicationType = LoanApplicationTypeDto.builder().build();
        given(mapper.toDto(loanApplicationTypeEntity)).willReturn(loanApplicationType);

        // Act
        var result = service.getById(37);

        // Assert
        assertEquals(loanApplicationType, result);
    }

    @Test
    void given_ALoanApplicationTypeExistsById_When_UpdateIsCalledWithALoanApplicationTypeDto_Then_TheLoanApplicationTypeIsUpdated() {

        // Arrange
        var loanApplicationTypeEntity = new LoanApplicationTypeEntity();
        given(repository.findById(71)).willReturn(Optional.of(loanApplicationTypeEntity));

        given(repository.save(loanApplicationTypeEntity)).willReturn(loanApplicationTypeEntity);

        var loanApplicationTypeDto = LoanApplicationTypeDto.builder().build();

        // Act
        var result = service.update(71, loanApplicationTypeDto);

        // Assert
        assertEquals(loanApplicationTypeEntity, result);

        then(mapper).should().updateEntity(loanApplicationTypeDto, loanApplicationTypeEntity);
    }

    @Test
    void given_ALoanApplicationTypeExistsById_When_DeleteByIdIsCalled_Then_ThatLoanApplicationTypeIsDeleted() {

        // Arrange
        var loanApplicationTypeEntity = new LoanApplicationTypeEntity();
        given(repository.findById(84)).willReturn(Optional.of(loanApplicationTypeEntity));

        given(repository.save(loanApplicationTypeEntity)).willReturn(loanApplicationTypeEntity);

        // Act
        var result = service.deleteById(84);

        // Assert
        assertEquals(loanApplicationTypeEntity, result);
        assertEquals(INACTIVE, loanApplicationTypeEntity.getStatus());
    }
}