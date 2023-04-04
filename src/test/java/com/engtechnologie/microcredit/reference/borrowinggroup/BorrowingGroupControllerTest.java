package com.engtechnologie.microcredit.reference.borrowinggroup;

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
class BorrowingGroupControllerTest {

    @Mock
    private BorrowingGroupService service;

    @InjectMocks
    private BorrowingGroupController controller;


    @Test
    void given_ABorrowingGroupDto_When_CreateIsCalled_Then_ABorrowingGroupIsCreatedAndItsPathReturnedWithCreatedStatus() {

        // Arrange
        var borrowingGroupEntity = BorrowingGroupEntity.builder().id(13).build();
        var borrowingGroupDto = BorrowingGroupDto.builder().build();
        given(service.create(borrowingGroupDto)).willReturn(borrowingGroupEntity);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        // Act
        var result = controller.create(borrowingGroupDto);

        // Assert
        assertEquals(CREATED, result.getStatusCode());
        assertEquals(URI.create("http://localhost/13"), result.getHeaders().getLocation());
    }

    @Test
    void given_ACollectionOfBorrowingGroupsExist_When_GetAllIsCalled_Then_ThoseBorrowingGroupsAreReturned() {

        // Arrange
        var borrowingGroups = singleton(BorrowingGroupDto.builder().build());
        given(service.getAll()).willReturn(borrowingGroups);

        // Act
        var result = controller.getAll();

        // Assert
        assertEquals(borrowingGroups, result);
    }

    @Test
    void given_ABorrowingGroupExistsById_When_GetByIdIsCalled_Then_ThatBorrowingGroupIsReturned() {

        // Arrange
        var borrowingGroup = BorrowingGroupDto.builder().build();
        given(service.getById(27)).willReturn(borrowingGroup);

        // Act
        var result = controller.getById(27);

        // Assert
        assertEquals(borrowingGroup, result);
    }

    @Test
    void given_ABorrowingGroupExistsById_When_UpdateIsCalledWithABorrowingGroupDto_Then_TheBorrowingGroupIsUpdatedAndNoContentStatusReturned() {

        // Arrange
        var borrowingGroupDto = BorrowingGroupDto.builder().build();

        // Act
        var result = controller.update(51, borrowingGroupDto);

        // Assert
        assertEquals(NO_CONTENT, result.getStatusCode());

        then(service).should().update(51, borrowingGroupDto);
    }

    @Test
    void given_ABorrowingGroupExistsById_When_DeleteByIdIsCalled_Then_ThatBorrowingGroupIsDeletedAndNoContentStatusReturned() {

        // Arrange
        var borrowingGroupId = 77;

        // Act
        var result = controller.deleteById(borrowingGroupId);

        // Assert
        assertEquals(NO_CONTENT, result.getStatusCode());

        then(service).should().deleteById(borrowingGroupId);
    }
}