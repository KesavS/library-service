package com.trenell.library.dao;

import com.trenell.library.model.Book;

import java.util.List;

public interface CrudDao<T, ID> {
  List<T> getAll();
  T findById(ID id);
  int create(T entity);
  int update(T entity);
  int delete(T entity);
}
