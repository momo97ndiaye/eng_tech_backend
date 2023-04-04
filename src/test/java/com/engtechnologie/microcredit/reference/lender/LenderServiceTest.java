package com.engtechnologie.microcredit.reference.lender;

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
class LenderServiceTest {

    @Mock
    private LenderMapper mapper;

    @Mock
    private LenderRepository repository;

    @InjectMocks
    private LenderService service;


    @Test
    void given_ALenderDto_When_CreateIsCalled_Then_ALenderIsCreatedAndReturned() {

        // Arrange
        var lenderDto = LenderDto.builder().build();
        var lenderEntity = LenderEntity.builder().build();

        given(mapper.toEntity(lenderDto)).willReturn(lenderEntity);
        given(repository.save(lenderEntity)).willReturn(lenderEntity);

        // Act
        var result = service.create(lenderDto);

        // Assert
        assertEquals(lenderEntity, result);
    }

    @Test
    void given_ACollectionOfLendersExist_When_GetAllIsCalled_Then_ThoseLendersAreReturned() {

        // Arrange
        var lenderEntities = singletonList(new LenderEntity());
        given(repository.findAll()).willReturn(lenderEntities);

        var lenders = singleton(LenderDto.builder().build());
        given(mapper.toDto(lenderEntities)).willReturn(lenders);


        // Act
        var result = service.getAll();

        // Assert
        assertEquals(lenders, result);
    }

    @Test
    void given_ALenderExistsById_When_GetByIdIsCalled_Then_ThatLenderIsReturned() {

        // Arrange
        var lenderEntity = new LenderEntity();
        given(repository.findById(35)).willReturn(Optional.of(lenderEntity));

        var lender = LenderDto.builder().build();
        given(mapper.toDto(lenderEntity)).willReturn(lender);

        // Act
        var result = service.getById(35);

        // Assert
        assertEquals(lender, result);
    }

    @Test
    void given_ALenderExistsById_When_UpdateIsCalledWithALenderDto_Then_TheLenderIsUpdated() {

        // Arrange
        var lenderEntity = new LenderEntity();
        given(repository.findById(61)).willReturn(Optional.of(lenderEntity));

        given(repository.save(lenderEntity)).willReturn(lenderEntity);

        var lenderDto = LenderDto.builder().build();

        // Act
        var result = service.update(61, lenderDto);

        // Assert
        assertEquals(lenderEntity, result);

        then(mapper).should().updateEntity(lenderDto, lenderEntity);
    }

    @Test
    void given_ALenderExistsById_When_DeleteByIdIsCalled_Then_ThatLenderIsDeleted() {

        // Arrange
        var lenderEntity = new LenderEntity();
        given(repository.findById(131)).willReturn(Optional.of(lenderEntity));

        given(repository.save(lenderEntity)).willReturn(lenderEntity);

        // Act
        var result = service.deleteById(131);

        // Assert
        assertEquals(lenderEntity, result);
        assertEquals(INACTIVE, lenderEntity.getStatus());
    }
}