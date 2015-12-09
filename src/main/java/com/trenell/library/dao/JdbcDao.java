package com.trenell.library.dao;

import com.trenell.library.model.Book;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;

public class JdbcDao implements CrudDao {
  private final NamedParameterJdbcTemplate jdbcTemplate;

  public JdbcDao(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Book> getAllBooks() {
    return jdbcTemplate.query(Queries.FIND_ALL.getQuery(), new BookRowMapper());
  }

  public Book findById(int id) {
    return jdbcTemplate.queryForObject(Queries.FIND_BY_ID.getQuery(), mapId(id), new BookRowMapper());
  }

  public void createBook(Book book) {
    jdbcTemplate.update(Queries.INSERT.getQuery(), mapSqlParameters(book));
  }

  public void updateBook(Book book) {
    jdbcTemplate.update(Queries.UPDATE.getQuery(), mapSqlParameters(book));
  }

  public void deleteBook(int isbn) {
    jdbcTemplate.update(Queries.INSERT.getQuery(), mapId(isbn));
  }

  private SqlParameterSource mapId(int id) {
    return new MapSqlParameterSource("id", id);
  }

  private SqlParameterSource mapSqlParameters(Book book) {
    return new MapSqlParameterSource("isbn", book.getIsbn())
        .addValue("title", book.getTitle())
        .addValue("author", book.getAuthor())
        .addValue("pageCount", book.getPageCount());
  }
}
