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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RepositoryConfig.class)
public class BookDaoTest {
  private static final Logger logger = LoggerFactory.getLogger(BookDaoTest.class);
  @Autowired
  private JdbcDao bookDao;

  @Test
  public void aBookCanBeRetrievedByPassingInTheIdOfTheBook() {
    Book book = bookDao.findById(1);
    logger.info(book.toString());
  }
}
