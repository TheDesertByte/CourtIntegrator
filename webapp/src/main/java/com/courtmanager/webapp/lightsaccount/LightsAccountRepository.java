package com.courtmanager.webapp.lightsaccount;

import com.courtmanager.webapp.databases.MsAccessDb;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class LightsAccountRepository {

  private final JdbcTemplate jdbcTemplate;

  public LightsAccountRepository() {
    this.jdbcTemplate = MsAccessDb.getInstance().getJdbcTemplate();
  }

  // Implement your CRUD operations as before
}
