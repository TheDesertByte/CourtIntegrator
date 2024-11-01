package com.courtmanager.webapp.lightsaccount;

import com.courtmanager.webapp.databases.MsAccessDb;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class LightsAccountRepository {

  private final Connection connection;

  public LightsAccountRepository() throws SQLException {
    MsAccessDb msAccessDb = new MsAccessDb();
    this.connection = msAccessDb.test();
  }

  public void selectAll() throws SQLException {
    try {
      String sql = "SELECT * FROM LightsAccount";

      Statement statement = connection.createStatement();
      ResultSet result = statement.executeQuery(sql);

      while (result.next()) {
        int id = result.getInt("Id");
        String fullname = result.getString("Name");

        System.out.println(id + ", " + fullname);
      }
    } catch (SQLException ex) {
      throw new SQLException(ex.getMessage().toString());
    }
  }
}
