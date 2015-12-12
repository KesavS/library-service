package com.trenell.library.dao;

import com.trenell.library.config.RepositoryConfig;
import com.trenell.library.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RepositoryConfig.class)
public class BookDaoTest {
  private static final Logger logger = LoggerFactory.getLogger(BookDaoTest.class);
  @Autowired
  private BookJdbcDao bookDao;

  @Test
  public void whenGivenAnIdABookIsReturned() {
    final int BOOK_ID = 1;
    Book book = bookDao.findById(1);

    assertEquals("When " + BOOK_ID + " is passed to findById, a book with and id of " + book.getId() + " is returned",
        BOOK_ID, book.getId());
  }

  @Test
  public void aBookThatCannotBeFoundReturnsADefaultBook() {
    final int DEFAULT_ID_ISBN_AND_PAGE_COUNT = 0;
    final String DEFAULT_AUTHOR_AND_TITLE = "";
    Book book = bookDao.findById(DEFAULT_ID_ISBN_AND_PAGE_COUNT);

    assertEquals(DEFAULT_ID_ISBN_AND_PAGE_COUNT, book.getId());
    assertEquals(DEFAULT_ID_ISBN_AND_PAGE_COUNT, book.getIsbn());
    assertEquals(DEFAULT_AUTHOR_AND_TITLE, book.getTitle());
    assertEquals(DEFAULT_AUTHOR_AND_TITLE, book.getTitle());
  }

  @Test
  public void getAllReturnsAllBooksInTheSystem()  {
    final int NUMBER_OF_BOOKS_IN_THE_SYSTEM = 55;
    List<Book> books = bookDao.getAll();

    assertEquals("The book database should contain " + NUMBER_OF_BOOKS_IN_THE_SYSTEM + " books. Calling getAll returned " +
        books.size() + " books", NUMBER_OF_BOOKS_IN_THE_SYSTEM, books.size());
  }

  @Test
  public void whenGivenAnExistingISBNABookIsReturned() {
    final long ISBN = 956813;
    Book book = bookDao.findByISBN(956813);

    assertEquals(ISBN, book.getIsbn());
  }

  @Test
  @Transactional
  @Rollback(true)
  public void aBookObjectIsSavedToTheSystemWhenPassedToCreate() {
    Book book = new Book.BookBuilder(12345678)
        .id(0)
        .author("Trenell")
        .title("This Book That Trenell Wrote")
        .pageCount(2)
        .build();
    bookDao.create(book);
    Book savedBook = bookDao.findByISBN(12345678);

    assertEquals(book.getIsbn(), savedBook.getIsbn());
  }

  @Test
  @Transactional
  @Rollback(true)
  public void whenPassedAnExistingBookUpdatesAreApplied() {
    Book bookFromDatabase = bookDao.findByISBN(956813);
    Book updatedBook = new Book.BookBuilder(bookFromDatabase.getIsbn())
        .id(bookFromDatabase.getId())
        .author("Trenell")
        .title(bookFromDatabase.getTitle())
        .pageCount(bookFromDatabase.getPageCount())
        .build();
    int affectedRows = bookDao.update(updatedBook);

    assertEquals(1, affectedRows);
  }
}
