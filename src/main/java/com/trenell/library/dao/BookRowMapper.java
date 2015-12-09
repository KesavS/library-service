package com.trenell.library.dao;

import com.trenell.library.model.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
  public Book mapRow(ResultSet resultSet, int i) throws SQLException {
    return new Book.BookBuilder(resultSet.getInt("ISBN"))
        .id(resultSet.getInt("ID"))
        .title(resultSet.getString("TITLE"))
        .author(resultSet.getString("AUTHOR"))
        .pageCount(resultSet.getInt("PAGE_COUNT"))
        .build();
  }
}
