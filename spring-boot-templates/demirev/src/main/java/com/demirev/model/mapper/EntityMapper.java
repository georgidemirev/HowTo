package com.demirev.model.mapper;

import java.util.List;

public interface EntityMapper<T1, T> {

    T toEntity (T1 dto);

    T1 toDto(T entity);

    List<T> toEntity(List<T1> dtoList);

    List<T1> toDto(List<T> entityList);
}
