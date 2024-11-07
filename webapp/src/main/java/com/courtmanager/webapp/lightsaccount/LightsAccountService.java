package com.courtmanager.webapp.lightsaccount;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courtmanager.webapp.interfaces.IRepository;
import com.courtmanager.webapp.interfaces.IService;

@Service
public class LightsAccountService implements IService {

  private final IRepository<LightsAccount> repository;

  @Autowired
  public LightsAccountService(IRepository<LightsAccount> repository) {
    this.repository = repository;
  }

  @Override
  public ArrayList<LightsAccount> getAll() throws SQLException {
    return this.repository.getAll();
  }

}
