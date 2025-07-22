package com.example.payment_transfer_system.services;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.payment_transfer_system.configuration.mappers.CustomMapper;
import com.example.payment_transfer_system.services.updaters.Updater;
import jakarta.persistence.EntityNotFoundException;
/**
 * Represents implementation of RestService Interface.
 * @param <T> - Entity object.
 * @param <ID> - id.
 * @param <D> - DTO object
 */
public abstract class RestServiceImpl<T, ID, D> implements RestService<T, ID, D>{
	
	protected final JpaRepository<T, ID> repository;
	
	protected final CustomMapper<T, D> mapper;
	private final Updater<T> updater;
	
	private Class<T> clazz;
	
	public RestServiceImpl(JpaRepository<T, ID> repository, Updater<T> updater, CustomMapper<T, D> mapper, Class<T> clazz) {
		this.repository =  repository;
		this.mapper = mapper;
		this.updater = updater;
		this.clazz = clazz;
	}
	
	@Override
	public List<D> findAll() {
		
		List<T> entityList = this.repository.findAll();
		List<D> dtoList = this.mapper.toDtoList(entityList);
		
		return dtoList;
	}

	@Override
	public D findById(ID id, Class<T> clazz) {
		Optional<T> optionalEntity = this.repository.findById(id);
		if(optionalEntity.isEmpty()) {
			throw new EntityNotFoundException(clazz.getSimpleName() + " with ID: " + id + " not found.");
		}
		
		Optional<D> optionalDto = optionalEntity.map(this.mapper::toDto);		// map Optional<Entity> to Optional<DTO>
		
		return optionalDto.get();
	}

	@Override
	public D save(D dto) {

		T toSaveEntity = this.mapper.toEntity(dto);
		T savedEntity = this.repository.save(toSaveEntity);
		
		D savedDto = this.mapper.toDto(savedEntity);
		
		return savedDto;	
	}

	@Override
	public D update(ID id, D dto) {
		
		T entity = this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException(this.clazz.getSimpleName() + " not found with ID: " + id));
		
		entity = this.updater.applyUpdate(entity, this.mapper.toEntity(dto));
		return this.mapper.toDto(this.repository.save(entity));
	}

	@Override
	public void deleteById(ID id, Class<T> clazz) {
		
		if(this.repository.existsById(id) == false) {
			throw new EntityNotFoundException(clazz.getSimpleName() + " not found with ID: " + id);
		}
		
		this.repository.deleteById(id);
		
	}

	
	
}
