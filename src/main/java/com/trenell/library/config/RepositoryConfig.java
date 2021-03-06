package com.trenell.library.config;

import com.trenell.library.dao.CrudDao;
import com.trenell.library.dao.BookJdbcDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class RepositoryConfig {
  @Bean
  public DataSource dataSource() {
    EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();

    return dbBuilder.setType(EmbeddedDatabaseType.H2)
        .addScript("db/sql/create-db.sql")
        .addScript("db/sql/insert-data.sql")
        .build();
  }

  @Bean
  public DataSourceTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean
  public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new NamedParameterJdbcTemplate(dataSource);
  }

  @Bean
  public CrudDao bookDao(NamedParameterJdbcTemplate jdbcTemplate) {
    return new BookJdbcDao(jdbcTemplate);
  }
}