package com.example.payment_transfer_system.configuration.mappers;

import java.util.List;

/**
 * Specification for MappStrut Mappers.
 * @param <T> - Entity class.
 * @param <D> - DTO class.
 */
public interface CustomMapper<T, D> {
	
	public D toDto(T entity);
	public T toEntity(D dto);
	public List<D> toDtoList(List<T> entities);
	public List<T> toEntityList(List<D> dtos);
	
}
