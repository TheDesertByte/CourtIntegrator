package com.courtmanager.webapp.lightsaccount;

import jakarta.enterprise.inject.Model;

@Model
public class LightsAccount {
  private int id;
  private String date;
  private int memNo;
  private String name;
  private float amount;
  private int type;
  private float balance;
  private String user;
  private int court;
  private int period;
  private boolean reversed;
  private int units;
  private int unitBalance;

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

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getMemNo() {
    return memNo;
  }

  public void setMemNo(int memNo) {
    this.memNo = memNo;
  }

  public float getAmount() {
    return amount;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public float getBalance() {
    return balance;
  }

  public void setBalance(float balance) {
    this.balance = balance;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public int getCourt() {
    return court;
  }

  public void setCourt(int court) {
    this.court = court;
  }

  public int getPeriod() {
    return period;
  }

  public void setPeriod(int period) {
    this.period = period;
  }

  public boolean isReversed() {
    return reversed;
  }

  public void setReversed(boolean reversed) {
    this.reversed = reversed;
  }

  public int getUnits() {
    return units;
  }

  public void setUnits(int units) {
    this.units = units;
  }

  public int getUnitBalance() {
    return unitBalance;
  }

  public void setUnitBalance(int unitBalance) {
    this.unitBalance = unitBalance;
  }

}
