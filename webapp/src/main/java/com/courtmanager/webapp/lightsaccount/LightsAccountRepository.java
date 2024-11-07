package com.courtmanager.webapp.lightsaccount;

import com.courtmanager.webapp.databases.MsAccessDb;
import com.courtmanager.webapp.interfaces.IRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.InvalidObjectException;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class LightsAccountRepository implements IRepository<LightsAccount> {

  private final Connection connection;

  @Autowired
  public LightsAccountRepository() throws SQLException {
    MsAccessDb msAccessDb = new MsAccessDb();
    this.connection = msAccessDb.test();
  }

  private ArrayList<LightsAccount> populateLightsAccountList(ResultSet result) throws SQLException {
    ArrayList<LightsAccount> accounts = new ArrayList<>();
    while (result.next()) {
      LightsAccount lightsAccount = new LightsAccount();
      lightsAccount.setName(result.getString("Name"));
      lightsAccount.setDate(result.getTimestamp("Date").toLocalDateTime());
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

  public ArrayList<LightsAccount> getAll() throws SQLException {
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

  @Override
  public void insert(LightsAccount lightsAccount) throws SQLException, InvalidObjectException {
    /**
     * Name, memNo and Amount MUST be set with the LightsAccount input param
     * Only standard params will be populated
     */

    if (lightsAccount.getName() == null || lightsAccount.getMemNo() == null || lightsAccount.getAmount() == null) {
      throw new InvalidObjectException(
          "name, memNo and amount fields of LightsAccount object MUST be set. The rest may be left empty");
    }
    // TODO validate member
    // TODO retrieve member balance from clublist in order to calculate balance.
    // set the balance in both clublist and in LightsAccounts. Perform a final check
    // for recon also compare with latest balance in LightsAccounts for member for 3
    // way check
    // TODO determine all tables that require balance update

    // TODO handle the id's
    // TODO handle user (i.e Operator)
    // TODO handle balances
    lightsAccount.setDate(LocalDateTime.now());
    lightsAccount.setType(7);
    lightsAccount.setCourt(0);
    lightsAccount.setUnits(null);
    lightsAccount.setPeriod(0);
    lightsAccount.setReversed(false);
    lightsAccount.setUnitBalance(null);
  }
}
