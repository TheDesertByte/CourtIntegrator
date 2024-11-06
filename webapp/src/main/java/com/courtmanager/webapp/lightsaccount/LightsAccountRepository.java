package com.courtmanager.webapp.lightsaccount;

import com.courtmanager.webapp.databases.MsAccessDb;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;

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

  private ArrayList<LightsAccount> populateLightsAccountList(ResultSet result) throws SQLException {
    ArrayList<LightsAccount> accounts = new ArrayList<>();
    while (result.next()) {
      LightsAccount lightsAccount = new LightsAccount();
      lightsAccount.setName(result.getString("Name"));
      lightsAccount.setDate(result.getDate("Date").toLocalDate());
      lightsAccount.setAmount(result.getFloat("Amount"));
      lightsAccount.setBalance(result.getFloat("Balance"));
      lightsAccount.setUser(result.getString("User"));
      lightsAccount.setType(result.getInt("Type"));
      lightsAccount.setMemNo(result.getInt("Mem_No"));
      lightsAccount.setReversed(result.getBoolean("Reversed"));
      accounts.add(lightsAccount);
    }
    return accounts;
  }

  public ArrayList<LightsAccount> selectAll() throws SQLException {
    try {
      String sql = "SELECT * FROM LightsAccount WHERE [Type] = 7 ORDER BY [Date] desc";
      // TODO replace println's with logging
      System.out.println("-------------------------------");
      System.out.println(sql);
      System.out.println("---------------------------------");
      Statement statement = connection.createStatement();
      ResultSet result = statement.executeQuery(sql);
      return this.populateLightsAccountList(result);
    } catch (SQLException ex) {
      throw new SQLException(ex.getMessage().toString());
    }
  }
}
