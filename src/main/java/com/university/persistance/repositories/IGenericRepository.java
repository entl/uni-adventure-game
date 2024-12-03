package com.university.persistance.repositories;

import java.util.List;

public interface IGenericRepository<T, ID> {
    T findById(ID id);
    List<T> findAll();
    int save(T entity);
    void update(T entity);
    void delete(ID id);
}
