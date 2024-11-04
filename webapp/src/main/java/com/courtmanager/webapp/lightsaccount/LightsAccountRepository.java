package com.courtmanager.webapp.lightsaccount;

import com.courtmanager.webapp.databases.MsAccessDb;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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

  public LightsAccount getById(int id) throws Exception {
    try {
      String sql = String.format("SELECT * FROM LightsAccount WHERE [Id]=%d", id);
      System.out.println(sql);
      Statement statement = connection.createStatement();
      ResultSet result = statement.executeQuery(sql);
      LightsAccount account = new LightsAccount();
      while (result.next()) {
        account.setId(result.getInt("Id"));
        account.setName(result.getString("Name"));
        account.setDate(result.getString("Date"));
        account.setMemNo(result.getInt("Mem_No"));
        account.setAmount(result.getFloat("Amount"));
        account.setBalance(result.getFloat("Balance"));
        account.setUser(result.getString("User"));
      }
      return account;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new Exception(e.getMessage());
    }
  }
}
