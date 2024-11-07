package com.courtmanager.webapp.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IService<T> {

  ArrayList<T> getAll() throws SQLException;

}
