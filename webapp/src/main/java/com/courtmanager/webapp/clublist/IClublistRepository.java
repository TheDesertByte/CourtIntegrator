package com.courtmanager.webapp.clublist;

import java.sql.SQLException;

import com.courtmanager.webapp.interfaces.IRepository;

public interface IClublistRepository extends IRepository<Clublist> {

  Clublist getById(int id) throws SQLException;
}
