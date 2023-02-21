package com.nikola.employees;

import java.util.List;

public interface Dao<T> {
    void insert(T e);
    void update(T e);
    List<T> getAll();
    T getById(int id);
    void delete(int id);
}
