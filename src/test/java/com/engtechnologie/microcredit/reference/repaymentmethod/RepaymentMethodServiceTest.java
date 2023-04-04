package com.engtechnologie.microcredit.reference.repaymentmethod;

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
class RepaymentMethodServiceTest {

    @Mock
    private RepaymentMethodMapper mapper;

    @Mock
    private RepaymentMethodRepository repository;

    @InjectMocks
    private RepaymentMethodService service;


    @Test
    void given_ARepaymentMethodDto_When_CreateIsCalled_Then_ARepaymentMethodIsCreatedAndReturned() {

        // Arrange
        var repaymentMethodDto = RepaymentMethodDto.builder().build();
        var repaymentMethodEntity = RepaymentMethodEntity.builder().build();

        given(mapper.toEntity(repaymentMethodDto)).willReturn(repaymentMethodEntity);
        given(repository.save(repaymentMethodEntity)).willReturn(repaymentMethodEntity);

        // Act
        var result = service.create(repaymentMethodDto);

        // Assert
        assertEquals(repaymentMethodEntity, result);
    }

    @Test
    void given_ACollectionOfRepaymentMethodsExist_When_GetAllIsCalled_Then_ThoseRepaymentMethodsAreReturned() {

        // Arrange
        var repaymentMethodEntities = singletonList(new RepaymentMethodEntity());
        given(repository.findAll()).willReturn(repaymentMethodEntities);

        var repaymentMethods = singleton(RepaymentMethodDto.builder().build());
        given(mapper.toDto(repaymentMethodEntities)).willReturn(repaymentMethods);


        // Act
        var result = service.getAll();

        // Assert
        assertEquals(repaymentMethods, result);
    }

    @Test
    void given_ARepaymentMethodExistsById_When_GetByIdIsCalled_Then_ThatRepaymentMethodIsReturned() {

        // Arrange
        var repaymentMethodEntity = new RepaymentMethodEntity();
        given(repository.findById(99)).willReturn(Optional.of(repaymentMethodEntity));

        var repaymentMethod = RepaymentMethodDto.builder().build();
        given(mapper.toDto(repaymentMethodEntity)).willReturn(repaymentMethod);

        // Act
        var result = service.getById(99);

        // Assert
        assertEquals(repaymentMethod, result);
    }

    @Test
    void given_ARepaymentMethodExistsById_When_UpdateIsCalledWithARepaymentMethodDto_Then_TheRepaymentMethodIsUpdated() {

        // Arrange
        var repaymentMethodEntity = new RepaymentMethodEntity();
        given(repository.findById(175)).willReturn(Optional.of(repaymentMethodEntity));

        given(repository.save(repaymentMethodEntity)).willReturn(repaymentMethodEntity);

        var repaymentMethodDto = RepaymentMethodDto.builder().build();

        // Act
        var result = service.update(175, repaymentMethodDto);

        // Assert
        assertEquals(repaymentMethodEntity, result);

        then(mapper).should().updateEntity(repaymentMethodDto, repaymentMethodEntity);
    }

    @Test
    void given_ARepaymentMethodExistsById_When_DeleteByIdIsCalled_Then_ThatRepaymentMethodIsDeleted() {

        // Arrange
        var repaymentMethodEntity = new RepaymentMethodEntity();
        given(repository.findById(255)).willReturn(Optional.of(repaymentMethodEntity));

        given(repository.save(repaymentMethodEntity)).willReturn(repaymentMethodEntity);

        // Act
        var result = service.deleteById(255);

        // Assert
        assertEquals(repaymentMethodEntity, result);
        assertEquals(INACTIVE, repaymentMethodEntity.getStatus());
    }
}