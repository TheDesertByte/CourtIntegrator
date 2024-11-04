package com.courtmanager.webapp.lightsaccount;

import com.courtmanager.webapp.databases.MsAccessDb;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class LightsAccountRepository {

  private final Connection connection;

  public LightsAccountRepository() throws SQLException {
    MsAccessDb msAccessDb = new MsAccessDb();
    this.connection = msAccessDb.test();
  }

  public ArrayList<LightsAccount> selectAll() throws SQLException {
    try {
      String sql = "SELECT * FROM LightsAccount WHERE [Type] = 7 ORDER BY [Date] desc";
      System.out.println("-------------------------------");
      System.out.println(sql);
      System.out.println("---------------------------------");

      Statement statement = connection.createStatement();
      ResultSet result = statement.executeQuery(sql);

      ArrayList<LightsAccount> accounts = new ArrayList<>();
      while (result.next()) {
        LightsAccount lightsAccount = new LightsAccount();
        lightsAccount.setName(result.getString("Name"));
        lightsAccount.setDate(result.getDate("Date"));
        lightsAccount.setAmount(result.getFloat("Amount"));
        lightsAccount.setBalance(result.getFloat("Balance"));
        lightsAccount.setUser(result.getString("User"));
        lightsAccount.setType(result.getInt("Type"));
        accounts.add(lightsAccount);
      }
      return accounts;
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
        account.setDate(result.getDate("Date"));
        account.setMemNo(result.getInt("Mem_No"));
        account.setAmount(result.getFloat("Amount"));
        account.setBalance(result.getFloat("Balance"));
        account.setUser(result.getString("User"));
        account.setType(result.getInt("Type"));
      }
      return account;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new Exception(e.getMessage());
    }
  }
}
