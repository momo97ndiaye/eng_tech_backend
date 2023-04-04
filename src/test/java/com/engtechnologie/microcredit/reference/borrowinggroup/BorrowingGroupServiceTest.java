package com.engtechnologie.microcredit.reference.borrowinggroup;

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
class BorrowingGroupServiceTest {

    @Mock
    private BorrowingGroupMapper mapper;

    @Mock
    private BorrowingGroupRepository repository;

    @InjectMocks
    private BorrowingGroupService service;


    @Test
    void given_ABorrowingGroupDto_When_CreateIsCalled_Then_ABorrowingGroupIsCreatedAndReturned() {

        // Arrange
        var borrowingGroupDto = BorrowingGroupDto.builder().build();
        var borrowingGroupEntity = BorrowingGroupEntity.builder().build();

        given(mapper.toEntity(borrowingGroupDto)).willReturn(borrowingGroupEntity);
        given(repository.save(borrowingGroupEntity)).willReturn(borrowingGroupEntity);

        // Act
        var result = service.create(borrowingGroupDto);

        // Assert
        assertEquals(borrowingGroupEntity, result);
    }

    @Test
    void given_ACollectionOfBorrowingGroupsExist_When_GetAllIsCalled_Then_ThoseBorrowingGroupsAreReturned() {

        // Arrange
        var borrowingGroupEntities = singletonList(new BorrowingGroupEntity());
        given(repository.findAll()).willReturn(borrowingGroupEntities);

        var borrowingGroups = singleton(BorrowingGroupDto.builder().build());
        given(mapper.toDto(borrowingGroupEntities)).willReturn(borrowingGroups);


        // Act
        var result = service.getAll();

        // Assert
        assertEquals(borrowingGroups, result);
    }

    @Test
    void given_ABorrowingGroupExistsById_When_GetByIdIsCalled_Then_ThatBorrowingGroupIsReturned() {

        // Arrange
        var borrowingGroupEntity = new BorrowingGroupEntity();
        given(repository.findById(88)).willReturn(Optional.of(borrowingGroupEntity));

        var borrowingGroup = BorrowingGroupDto.builder().build();
        given(mapper.toDto(borrowingGroupEntity)).willReturn(borrowingGroup);

        // Act
        var result = service.getById(88);

        // Assert
        assertEquals(borrowingGroup, result);
    }

    @Test
    void given_ABorrowingGroupExistsById_When_UpdateIsCalledWithABorrowingGroupDto_Then_TheBorrowingGroupIsUpdated() {

        // Arrange
        var borrowingGroupEntity = new BorrowingGroupEntity();
        given(repository.findById(101)).willReturn(Optional.of(borrowingGroupEntity));

        given(repository.save(borrowingGroupEntity)).willReturn(borrowingGroupEntity);

        var borrowingGroupDto = BorrowingGroupDto.builder().build();

        // Act
        var result = service.update(101, borrowingGroupDto);

        // Assert
        assertEquals(borrowingGroupEntity, result);

        then(mapper).should().updateEntity(borrowingGroupDto, borrowingGroupEntity);
    }

    @Test
    void given_ABorrowingGroupExistsById_When_DeleteByIdIsCalled_Then_ThatBorrowingGroupIsDeleted() {

        // Arrange
        var borrowingGroupEntity = new BorrowingGroupEntity();
        given(repository.findById(31)).willReturn(Optional.of(borrowingGroupEntity));

        given(repository.save(borrowingGroupEntity)).willReturn(borrowingGroupEntity);

        // Act
        var result = service.deleteById(31);

        // Assert
        assertEquals(borrowingGroupEntity, result);
        assertEquals(INACTIVE, borrowingGroupEntity.getStatus());
    }
}