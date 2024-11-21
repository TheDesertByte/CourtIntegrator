package com.courtmanager.webapp.clublist;

public class Clublist {
  private Integer id;
  private String surname;
  private String first;
  private Integer memNo;
  private Float balance;

  public Float getBalance() {
    return balance;
  }

  public void setBalance(Float balance) {
    this.balance = balance;
  }

  public Clublist() {

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getFirst() {
    return first;
  }

  public void setFirst(String first) {
    this.first = first;
  }

  public Integer getMemNo() {
    return memNo;
  }

  public void setMemNo(Integer memNo) {
    this.memNo = memNo;
  }

}
