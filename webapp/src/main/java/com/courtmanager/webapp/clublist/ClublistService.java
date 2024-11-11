package com.courtmanager.webapp.clublist;

import java.io.InvalidObjectException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClublistService implements IClublistService {

  private final IClublistRepository repository;

  @Autowired
  public ClublistService(IClublistRepository repository) {
    this.repository = repository;
  }

  @Override
  public ArrayList<Clublist> getAll() throws SQLException {
    return this.repository.getAll();
  }

  @Override
  public void insert(Clublist object) throws SQLException, InvalidObjectException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'insert'");
  }

  @Override
  public Clublist getById(int memberId) throws SQLException {
    return this.repository.getById(memberId);
  }

}
