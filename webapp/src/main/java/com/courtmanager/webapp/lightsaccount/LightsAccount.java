package com.courtmanager.webapp.lightsaccount;

import java.time.LocalDateTime;

import jakarta.enterprise.inject.Model;

@Model
public class LightsAccount {
  private Integer id;
  private LocalDateTime date;
  private Integer memNo;
  private String name;
  private Float amount;
  private Integer type;
  private Float balance;
  private String user;
  private Integer court;
  private Integer period;
  private boolean reversed;
  private Integer units;
  private Integer unitBalance;

  public LightsAccount() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public Integer getMemNo() {
    return memNo;
  }

  public void setMemNo(Integer memNo) {
    this.memNo = memNo;
  }

  public Float getAmount() {
    return amount;
  }

  public void setAmount(Float amount) {
    this.amount = amount;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Float getBalance() {
    return balance;
  }

  public void setBalance(Float balance) {
    this.balance = balance;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public Integer getCourt() {
    return court;
  }

  public void setCourt(Integer court) {
    this.court = court;
  }

  public Integer getPeriod() {
    return period;
  }

  public void setPeriod(Integer period) {
    this.period = period;
  }

  public boolean isReversed() {
    return reversed;
  }

  public void setReversed(boolean reversed) {
    this.reversed = reversed;
  }

  public Integer getUnits() {
    return units;
  }

  public void setUnits(Integer units) {
    this.units = units;
  }

  public Integer getUnitBalance() {
    return unitBalance;
  }

  public void setUnitBalance(Integer unitBalance) {
    this.unitBalance = unitBalance;
  }

}
