package com.trenell.library.dao;

import com.trenell.library.config.RepositoryConfig;
import com.trenell.library.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
}
