package com.courtmanager.webapp.clublist;

import java.io.InvalidObjectException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.courtmanager.webapp.databases.MsAccessDb;

// TODO logging
// TODO error handling
// TODO ACID compliance
@Repository
public class ClublistRepository implements IClublistRepository {

  private final Connection connection;

  @Autowired
  public ClublistRepository() throws SQLException {
    MsAccessDb msAccessDb = new MsAccessDb();
    this.connection = msAccessDb.test();
  }

  @Override
  public ArrayList<Clublist> getAll() throws SQLException {
    String sql = "SELECT * FROM Clublist";
    Statement statement = connection.createStatement();
    return populateClublist(statement.executeQuery(sql));
  }

  @Override
  public void insert(Clublist object) throws SQLException, InvalidObjectException {
    // TODO Implement insert method
    throw new UnsupportedOperationException("Unimplemented method 'insert'");
  }

  @Override
  public Clublist getById(int id) throws SQLException {
    String sql = "SELECT * FROM Clublist WHERE Id = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setInt(1, id);
    ResultSet results = preparedStatement.executeQuery();

    if (results.next()) {
      Clublist member = new Clublist();
      member.setId(results.getInt("Id"));
      member.setFirst(results.getString("First"));
      member.setSurname(results.getString("Surname"));
      member.setMemNo(results.getInt("Mem_No"));
      member.setBalance(results.getFloat("S_Credit"));
      return member;
    } else {
      return null;
    }
  }

  private ArrayList<Clublist> populateClublist(ResultSet results) throws SQLException {
    ArrayList<Clublist> clublist = new ArrayList<>();
    while (results.next()) {
      Clublist member = new Clublist();
      member.setId(results.getInt("Id"));
      member.setFirst(results.getString("First"));
      member.setSurname(results.getString("Surname"));
      member.setMemNo(results.getInt("Mem_No"));
      member.setBalance(results.getFloat("S_Credit"));
      clublist.add(member);
    }
    return clublist;
  }

}
