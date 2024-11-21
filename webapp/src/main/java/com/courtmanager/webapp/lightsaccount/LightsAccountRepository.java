package com.courtmanager.webapp.lightsaccount;

import com.courtmanager.webapp.databases.MsAccessDb;
import com.courtmanager.webapp.interfaces.IRepository;
import com.courtmanager.webapp.clublist.Clublist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.InvalidObjectException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO logging
// TODO error handling
// TODO ACID compliance
@Repository
public class LightsAccountRepository implements IRepository<LightsAccount> {

  private final Connection connection;

  @Autowired
  public LightsAccountRepository() throws SQLException {
    MsAccessDb msAccessDb = new MsAccessDb();
    this.connection = msAccessDb.test();
  }

  public ArrayList<LightsAccount> getAll() throws SQLException {
    try {
      String sql = "SELECT * FROM LightsAccount WHERE [Type] = 7 ORDER BY [Date] desc";
      Statement statement = connection.createStatement();
      ResultSet result = statement.executeQuery(sql);
      return this.populateGetLightsAccountList(result);
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

    if (this.validateUserInput(lightsAccount) && this.isMember(lightsAccount)) {
      this.populateObjectForInsertion(lightsAccount);
      String sql = "INSERT INTO LightsAccount (Id, Date, Mem_No, Name, Amount, Type, Balance, User, Court, Period, Reversed, Units, UnitBalance) "
          +
          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      this.prepareInsertStatement(lightsAccount, sql).executeUpdate();
    }

  }

  private ArrayList<LightsAccount> populateGetLightsAccountList(ResultSet result) throws SQLException {
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

  private boolean validateUserInput(LightsAccount lightsAccount) throws InvalidObjectException {
    if (lightsAccount.getName() == null || lightsAccount.getMemNo() == null || lightsAccount.getAmount() == null) {
      throw new InvalidObjectException(
          "name, memNo and amount fields of LightsAccount object MUST be set. The rest may be left empty");
    }
    return true;
  }

  private void populateObjectForInsertion(LightsAccount lightsAccount) {
    // TODO retrieve member balance from clublist in order to calculate balance.
    // set the balance in both clublist and in LightsAccounts. Perform a final check
    // for recon also compare with latest balance in LightsAccounts for member for 3
    // way check
    lightsAccount.setDate(LocalDateTime.now());
    lightsAccount.setType(7);
    lightsAccount.setCourt(0);
    lightsAccount.setUnits(null);
    lightsAccount.setPeriod(0);
    lightsAccount.setReversed(false);
    lightsAccount.setUnitBalance(null);
  }

  private void getMemberBalanceFromClublist() {

  }

  private PreparedStatement prepareInsertStatement(LightsAccount lightsAccount, String sql) throws SQLException {
    int generatedId = this.generateLightsAccountId();
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setInt(1, generatedId);
      preparedStatement.setTimestamp(2,
          lightsAccount.getDate() != null ? Timestamp.valueOf(lightsAccount.getDate()) : null);
      preparedStatement.setInt(3, lightsAccount.getMemNo());
      preparedStatement.setString(4, lightsAccount.getName());
      preparedStatement.setFloat(5, lightsAccount.getAmount());
      if (lightsAccount.getType() != null) {
        preparedStatement.setInt(6, lightsAccount.getType());
      } else {
        preparedStatement.setNull(6, java.sql.Types.INTEGER);
      }
      if (lightsAccount.getBalance() != null) {
        preparedStatement.setFloat(7, lightsAccount.getBalance());
      } else {
        preparedStatement.setNull(7, java.sql.Types.FLOAT);
      }
      preparedStatement.setString(8, lightsAccount.getUser());
      preparedStatement.setInt(9, lightsAccount.getCourt());
      preparedStatement.setInt(10, lightsAccount.getPeriod());
      preparedStatement.setInt(11, lightsAccount.isReversed() ? 1 : 0);
      if (lightsAccount.getUnits() != null) {
        preparedStatement.setFloat(12, lightsAccount.getUnits());
      } else {
        preparedStatement.setNull(12, java.sql.Types.FLOAT);
      }
      if (lightsAccount.getUnitBalance() != null) {
        preparedStatement.setFloat(13, lightsAccount.getUnitBalance());
      } else {
        preparedStatement.setNull(13, java.sql.Types.FLOAT);
      }
      return preparedStatement;
    }

  }

  private int generateLightsAccountId() throws SQLException {
    int newId = 1;
    String idQuery = "SELECT MAX(Id) FROM LightsAccount";
    try (Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(idQuery)) {
      if (resultSet.next()) {
        newId = resultSet.getInt(1) + 1;
      }
    }
    return newId;
  }

  private boolean isMember(LightsAccount lightsAccount) throws SQLException {
    // TODO double check if isMember is correct
    ArrayList<Clublist> clublist = this.populateClubListObject(this.getClublistResultSet());

    var filtered = clublist.stream();
    if (lightsAccount.getName() != null && !lightsAccount.getName().isEmpty()) {
      filtered = filtered.filter(c -> c.getSurname().toLowerCase().contains(lightsAccount.getName().toLowerCase())
          || c.getFirst().toLowerCase().contains(lightsAccount.getName().toLowerCase()));
    }

    if (lightsAccount.getMemNo() != null && lightsAccount.getMemNo() != 0) {
      filtered = filtered.filter(c -> {
        return (c.getMemNo() == lightsAccount.getMemNo());
      });
    }

    if (!filtered.toList().isEmpty()) {
      return true;
    }
    return false;

  }

  public ResultSet getClublistResultSet() throws SQLException {
    // TODO use clublist Repository instead
    String sql = "SELECT Id, Surname, First, Mem_No FROM Clublist";
    Statement statement = connection.createStatement();
    return statement.executeQuery(sql);
  }

  private ArrayList<Clublist> populateClubListObject(ResultSet result) throws SQLException {
    ArrayList<Clublist> members = new ArrayList<>();
    while (result.next()) {
      Clublist member = new Clublist();
      member.setId(result.getInt("Id"));
      member.setSurname(result.getString("Surname"));
      member.setFirst(result.getString("First"));
      member.setMemNo(result.getInt("Mem_No"));
      members.add(member);
    }
    return members;
  }

}
