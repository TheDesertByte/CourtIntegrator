package com.courtmanager.webapp.interfaces;

import java.io.InvalidObjectException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IRepository<T> {

  ArrayList<T> getAll() throws SQLException;

  void insert(T object) throws SQLException, InvalidObjectException;

}
