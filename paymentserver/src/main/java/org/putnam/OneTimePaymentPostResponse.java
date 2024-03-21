package org.putnam;

public class OneTimePaymentPostResponse {

  private double newBalance;
  private String nextDueDate;

  public double getNewBalance() {
    return newBalance;
  }

  public String getNextDueDate() {
    return nextDueDate;
  }

  public void setNewBalance(double newBalance) {
    this.newBalance = newBalance;
  }

  public void setNextDueDate(String nextDueDate) {
    this.nextDueDate = nextDueDate;
  }

  public OneTimePaymentPostResponse withNewBalance(double newBalance) {
    this.newBalance = newBalance;
    return this;
  }

  public OneTimePaymentPostResponse withNextDueDate(String nextDueDate) {
    this.nextDueDate = nextDueDate;
    return this;
  }

}
