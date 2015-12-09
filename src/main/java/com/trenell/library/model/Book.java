package com.trenell.library.model;

import com.google.common.base.Strings;

public class Book {
  private final int id;
  private final long isbn;
  private final String title;
  private final String author;
  private final int pageCount;

  private Book(BookBuilder builder) {
    this.id = builder.id;
    this.isbn = builder.isbn;
    this.title = builder.title;
    this.author = builder.author;
    this.pageCount = builder.pageCount;
  }

  public static class BookBuilder {
    private int id = 0;
    private final int isbn;
    private String title = "";
    private String author = "";
    private int pageCount = 0;

    public BookBuilder(int isbn) {
      this.isbn = isbn;
    }

    public BookBuilder id(int id){
      if(id != 0) {
        this.id = id;
      }

      return this;
    }

    public BookBuilder title(String title) {
      if(!Strings.isNullOrEmpty(title)) {
        this.title = title;
      }

      return this;
    }

    public BookBuilder author(String author) {
      if(!Strings.isNullOrEmpty(author)) {
        this.author = author;
      }

      return this;
    }

    public BookBuilder pageCount(int pageCount) {
      if(pageCount != 0) {
        this.pageCount = pageCount;
      }

      return this;
    }

    public Book build() {
      return new Book(this);
    }
  }

  public long getIsbn() {
    return isbn;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public int getPageCount() {
    return pageCount;
  }

  @Override
  public boolean equals(Object otherBook) {
    if (this == otherBook) return true;
    if (otherBook == null || getClass() != otherBook.getClass()) return false;

    Book book = (Book) otherBook;

    if (id != book.id) return false;
    if (isbn != book.isbn) return false;
    if (pageCount != book.pageCount) return false;
    if (!title.equals(book.title)) return false;

    return author.equals(book.author);

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + title.hashCode();
    result = 31 * result + author.hashCode();
    result = 31 * result + pageCount;

    return result;
  }

  @Override
  public String toString() {
    return "Book{" +
        "id: " + id +
        ", isbn: " + isbn +
        ", title: '" + title + '\'' +
        ", author: '" + author + '\'' +
        ", pageCount: " + pageCount +
        '}';
  }
}
