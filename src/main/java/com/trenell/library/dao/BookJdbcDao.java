package com.trenell.library.dao;

import com.google.common.base.Throwables;
import com.trenell.library.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.ArrayList;
import java.util.List;

public class BookJdbcDao implements CrudDao<Book, Integer> {
  private static final Logger LOGGER = LoggerFactory.getLogger(BookJdbcDao.class);
  private final NamedParameterJdbcTemplate jdbcTemplate;

  public BookJdbcDao(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Book> getAll() {
    try {
      return jdbcTemplate.query(Queries.FIND_ALL.getQuery(), new BookRowMapper());
    } catch(DataAccessException e) {
      LOGGER.error("An error has occurred while trying to retrieve books from the system: \n" + Throwables.getStackTraceAsString(e), e);
    }
    return new ArrayList<>();
  }

  @Override
  public Book findById(Integer id) {
    try {
      return jdbcTemplate.queryForObject(Queries.FIND_BY_ID.getQuery(), new MapSqlParameterSource("id", id), new BookRowMapper());
    } catch(DataAccessException e) {
      LOGGER.error("An error has occurred while trying to retrieve book with and ID of " + id +
          " from the system: \n" + Throwables.getStackTraceAsString(e), e);
    }

    return new Book.BookBuilder(0).build();
  }

  public Book findByISBN(long isbn) {
    try {
      return jdbcTemplate.queryForObject(Queries.FIND_BY_ISBN.getQuery(), new MapSqlParameterSource("isbn", isbn), new BookRowMapper());
    } catch(DataAccessException e) {
      LOGGER.error("An error has occurred while trying to retrieve book with an ISBN of " + isbn +
          " from the system: \n" + Throwables.getStackTraceAsString(e));
    }

    return new Book.BookBuilder(0).build();
  }

  @Override
  public int create(Book book) {
    return executeUpdate(book, Queries.INSERT.getQuery());
  }

  @Override
  public int update(Book book) {
    try {
      return executeUpdate(book, Queries.UPDATE.getQuery());
    } catch(DataAccessException e) {
      LOGGER.error("An error has occurred while trying to update the following book\n" +
          book.toString() + ": \n" + Throwables.getRootCause(e) + "\n" + Throwables.getStackTraceAsString(e), e);
    }

    return 0;
  }

  @Override
  public int delete(Book book) {
    return executeUpdate(book, Queries.DELETE.getQuery());
  }

  public int executeUpdate(Book book, String query) {
    int affectedRows = 0;
    try {
      affectedRows = jdbcTemplate.update(query, createParameterMap(book));
    } catch(DataAccessException e) {
      LOGGER.error("An error has occurred while trying to create the following book\n" +
          book.toString() + ": \n" + Throwables.getStackTraceAsString(e), e);
    }

    return affectedRows;
  }

  private SqlParameterSource createParameterMap(Book book) {
    return new MapSqlParameterSource("isbn", book.getIsbn())
        .addValue("id", book.getId())
        .addValue("title", book.getTitle())
        .addValue("author", book.getAuthor())
        .addValue("pageCount", book.getPageCount());
  }
}
