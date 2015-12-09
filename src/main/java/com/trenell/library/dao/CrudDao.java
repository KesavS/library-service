package com.trenell.library.dao;

import com.trenell.library.model.Book;

import java.util.List;

public interface CrudDao {
  List<Book> getAllBooks();
  Book findById(int id);
  void createBook(Book book);
  void updateBook(Book book);
  void deleteBook(int id);
}
