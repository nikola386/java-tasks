package com.nikola.sqltask;

import java.util.List;

public interface Dao<T> {
    void insert(T e);
    void update(T e);
    List<T> getAll();
    T getById(int id);
    void delete(int id);
}
