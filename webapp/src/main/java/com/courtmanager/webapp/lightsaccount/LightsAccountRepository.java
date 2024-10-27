package com.courtmanager.webapp.lightsaccount;

import com.courtmanager.webapp.databases.MsAccessDb;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class LightsAccountRepository {

  private final JdbcTemplate jdbcTemplate;

  public LightsAccountRepository() {
    this.jdbcTemplate = MsAccessDb.getInstance().getJdbcTemplate();
  }

  // Implement your CRUD operations as before
}
