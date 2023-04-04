package com.engtechnologie.microcredit.reference.division;

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
class DivisionServiceTest {

    @Mock
    private DivisionMapper mapper;

    @Mock
    private DivisionRepository repository;

    @InjectMocks
    private DivisionService service;


    @Test
    void given_ADivisionDto_When_CreateIsCalled_Then_ADivisionIsCreatedAndReturned() {

        // Arrange
        var divisionDto = DivisionDto.builder().build();
        var divisionEntity = DivisionEntity.builder().build();

        given(mapper.toEntity(divisionDto)).willReturn(divisionEntity);
        given(repository.save(divisionEntity)).willReturn(divisionEntity);

        // Act
        var result = service.create(divisionDto);

        // Assert
        assertEquals(divisionEntity, result);
    }

    @Test
    void given_ACollectionOfDivisionsExist_When_GetAllIsCalled_Then_ThoseDivisionsAreReturned() {

        // Arrange
        var divisionEntities = singletonList(new DivisionEntity());
        given(repository.findAll()).willReturn(divisionEntities);

        var divisions = singleton(DivisionDto.builder().build());
        given(mapper.toDto(divisionEntities)).willReturn(divisions);


        // Act
        var result = service.getAll();

        // Assert
        assertEquals(divisions, result);
    }

    @Test
    void given_ADivisionExistsById_When_GetByIdIsCalled_Then_ThatDivisionIsReturned() {

        // Arrange
        var divisionEntity = new DivisionEntity();
        given(repository.findById(96)).willReturn(Optional.of(divisionEntity));

        var division = DivisionDto.builder().build();
        given(mapper.toDto(divisionEntity)).willReturn(division);

        // Act
        var result = service.getById(96);

        // Assert
        assertEquals(division, result);
    }

    @Test
    void given_ADivisionExistsById_When_UpdateIsCalledWithADivisionDto_Then_TheDivisionIsUpdated() {

        // Arrange
        var divisionEntity = new DivisionEntity();
        given(repository.findById(27)).willReturn(Optional.of(divisionEntity));

        given(repository.save(divisionEntity)).willReturn(divisionEntity);

        var divisionDto = DivisionDto.builder().build();

        // Act
        var result = service.update(27, divisionDto);

        // Assert
        assertEquals(divisionEntity, result);

        then(mapper).should().updateEntity(divisionDto, divisionEntity);
    }

    @Test
    void given_ADivisionExistsById_When_DeleteByIdIsCalled_Then_ThatDivisionIsDeleted() {

        // Arrange
        var divisionEntity = new DivisionEntity();
        given(repository.findById(81)).willReturn(Optional.of(divisionEntity));

        given(repository.save(divisionEntity)).willReturn(divisionEntity);

        // Act
        var result = service.deleteById(81);

        // Assert
        assertEquals(divisionEntity, result);
        assertEquals(INACTIVE, divisionEntity.getStatus());
    }
}