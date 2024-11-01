package com.courtmanager.webapp.lightsaccount;

import jakarta.enterprise.inject.Model;

@Model
public class LightsAccount {
  private int id;
  private String name;

  public LightsAccount() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
