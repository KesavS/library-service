package com.trenell.library.dao;

public enum Queries {
  FIND_ALL("SELECT ID, ISBN, TITLE, AUTHOR, PAGE_COUNT FROM BOOKS"),
  FIND_BY_ID("SELECT ID, ISBN, TITLE, AUTHOR, PAGE_COUNT FROM BOOKS WHERE ID=:id"),
  FIND_BY_ISBN("SELECT ID, ISBN, TITLE, AUTHOR, PAGE_COUNT FROM BOOKS WHERE ISBN=:isbn"),
  INSERT("INSERT INTO BOOKS VALUES(NULL, :isbn,:title,:author,:pageCount)"),
  UPDATE("UPDATE BOOKS SET ISBN=:isbn, TITLE=:title, AUTHOR=:author, PAGE_COUNT=:pageCount WHERE ID=:id"),
  DELETE("DELETE FROM BOOKS WHERE ISBN = :id");

  private final String query;

  Queries(String query) {
    this.query = query;
  }

  public String getQuery() {
    return query;
  }
}