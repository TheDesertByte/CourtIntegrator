package com.courtmanager.webapp.interfaces;

import java.io.InvalidObjectException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.courtmanager.webapp.clublist.Clublist;

public interface IService<T> {

  ArrayList<T> getAll() throws SQLException;

  void insert(T object) throws SQLException, InvalidObjectException;

}
