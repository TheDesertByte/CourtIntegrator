package com.courtmanager.webapp.lightsaccount;

import java.io.InvalidObjectException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courtmanager.webapp.clublist.Clublist;
import com.courtmanager.webapp.interfaces.IRepository;
import com.courtmanager.webapp.interfaces.IService;

@Service
public class LightsAccountService implements IService<LightsAccount> {

  private final IRepository<LightsAccount> repository;

  @Autowired
  public LightsAccountService(IRepository<LightsAccount> repository) {
    this.repository = repository;
  }

  @Override
  public ArrayList<LightsAccount> getAll() throws SQLException {
    return this.repository.getAll();
  }

  @Override
  public void insert(LightsAccount lightsAccount) throws SQLException, InvalidObjectException {
    this.repository.insert(lightsAccount);
  }

}
