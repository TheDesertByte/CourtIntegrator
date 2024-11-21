package com.courtmanager.webapp.clublist;

import java.sql.SQLException;

import com.courtmanager.webapp.interfaces.IService;

public interface IClublistService extends IService<Clublist> {
  Clublist getById(int memberId) throws SQLException;
}
