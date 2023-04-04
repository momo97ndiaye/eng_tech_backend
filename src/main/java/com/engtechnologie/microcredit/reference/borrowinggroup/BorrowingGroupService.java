package com.engtechnologie.microcredit.reference.borrowinggroup;

import com.engtechnologie.microcredit.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.engtechnologie.microcredit.enumeration.ReferenceStatus.INACTIVE;

@Service
@RequiredArgsConstructor
class BorrowingGroupService {

	private final BorrowingGroupRepository repository;

	private final BorrowingGroupMapper mapper;


	BorrowingGroupEntity create(BorrowingGroupDto borrowingGroupDto) {

		var borrowingGroupEntity = mapper.toEntity(borrowingGroupDto);
		return repository.save(borrowingGroupEntity);
	}

	Collection<BorrowingGroupDto> getAll() {
		return mapper.toDto(repository.findAll());
	}

	BorrowingGroupDto getById(Integer borrowingGroupId) {
		return mapper.toDto(loadEntity(borrowingGroupId));
	}

	BorrowingGroupEntity update(Integer borrowingGroupId, BorrowingGroupDto borrowingGroupDto) {

		var borrowingGroupEntity = loadEntity(borrowingGroupId);
		mapper.updateEntity(borrowingGroupDto, borrowingGroupEntity);
		return repository.save(borrowingGroupEntity);
	}

	BorrowingGroupEntity deleteById(Integer borrowingGroupId) {

		var borrowingGroupEntity = loadEntity(borrowingGroupId);
		borrowingGroupEntity.setStatus(INACTIVE);
		return repository.save(borrowingGroupEntity);
	}

	private BorrowingGroupEntity loadEntity(Integer entityId) {
		return repository.findById(entityId).orElseThrow(ResourceNotFoundException::new);
	}
}
