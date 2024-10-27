package com.courtmanager.webapp.lightsaccount;

import com.courtmanager.webapp.databases.MsAccessDb;
import org.springframework.stereotype.Repository;
import javax.swing.tree.RowMapper;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class LightsAccountRepository {

  private final JdbcTemplate jdbcTemplate;

  public LightsAccountRepository() {
    this.jdbcTemplate = MsAccessDb.getInstance().getJdbcTemplate();
  }

  public void selectAll() {
    // TODO implement
    throw new NotImplementedException("Todo");
  }
}
