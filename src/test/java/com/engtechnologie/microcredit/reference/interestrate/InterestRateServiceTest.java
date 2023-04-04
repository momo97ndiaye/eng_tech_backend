package com.engtechnologie.microcredit.reference.interestrate;

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
class InterestRateServiceTest {
    @Mock
    private InterestRateMapper mapper;

    @Mock
    private InterestRateRepository repository;

    @InjectMocks
    private InterestRateService service;


    @Test
    void given_AnInterestRateDto_When_CreateIsCalled_Then_ABanInterestRateIsCreatedAndReturned() {

        // Arrange
        var interestRateDto = InterestRateDto.builder().build();
        var interestRateEntity = InterestRateEntity.builder().build();

        given(mapper.toEntity(interestRateDto)).willReturn(interestRateEntity);
        given(repository.save(interestRateEntity)).willReturn(interestRateEntity);

        // Act
        var result = service.create(interestRateDto);

        // Assert
        assertEquals(interestRateEntity, result);
    }

    @Test
    void given_ACollectionOfInterestRatesExist_When_GetAllIsCalled_Then_ThoseInterestRatesAreReturned() {

        // Arrange
        var interestRateEntities = singletonList(new InterestRateEntity());
        given(repository.findAll()).willReturn(interestRateEntities);

        var interestRates = singleton(InterestRateDto.builder().build());
        given(mapper.toDto(interestRateEntities)).willReturn(interestRates);


        // Act
        var result = service.getAll();

        // Assert
        assertEquals(interestRates, result);
    }

    @Test
    void given_AnInterestRateExistsById_When_GetByIdIsCalled_Then_ThatInterestRateIsReturned() {

        // Arrange
        var interestRateEntity = new InterestRateEntity();
        given(repository.findById(49)).willReturn(Optional.of(interestRateEntity));

        var interestRate = InterestRateDto.builder().build();
        given(mapper.toDto(interestRateEntity)).willReturn(interestRate);

        // Act
        var result = service.getById(49);

        // Assert
        assertEquals(interestRate, result);
    }

    @Test
    void given_AnInterestRateExistsById_When_UpdateIsCalledWithAnInterestRateDto_Then_TheInterestRateIsUpdated() {

        // Arrange
        var interestRateEntity = new InterestRateEntity();
        given(repository.findById(65)).willReturn(Optional.of(interestRateEntity));

        given(repository.save(interestRateEntity)).willReturn(interestRateEntity);

        var interestRateDto = InterestRateDto.builder().build();

        // Act
        var result = service.update(65, interestRateDto);

        // Assert
        assertEquals(interestRateEntity, result);

        then(mapper).should().updateEntity(interestRateDto, interestRateEntity);
    }

    @Test
    void given_AnInterestRateExistsById_When_DeleteByIdIsCalled_Then_ThatInterestRateIsDeleted() {

        // Arrange
        var interestRateEntity = new InterestRateEntity();
        given(repository.findById(14)).willReturn(Optional.of(interestRateEntity));

        given(repository.save(interestRateEntity)).willReturn(interestRateEntity);

        // Act
        var result = service.deleteById(14);

        // Assert
        assertEquals(interestRateEntity, result);
        assertEquals(INACTIVE, interestRateEntity.getStatus());
    }
}