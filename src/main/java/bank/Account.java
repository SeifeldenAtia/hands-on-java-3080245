package bank;

import bank.exceptions.AmountException;

public class Account {

  private int id;
  private String type;
  private double balance;

  public Account(int id, String type, double balance) {
    this.id = id;
    this.type = type;
    this.balance = balance;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public void deposit(double amount) throws AmountException {
    if (amount < 1) {
      throw new AmountException("The minimum deposit amout must be greater than 1");
    } else {
      double newBalance = balance + amount;
      setBalance(newBalance);
      DataSource.updateBalance(id, newBalance);
    }
  }

  public void withdraw(double amount) throws AmountException {
    if (amount < 0) {
      throw new AmountException("The withdrawal amount must be greater than 0.");
    } else if (amount > balance) {
      throw new AmountException("You don't have suffiecient funds for this withdrawal.");
    } else {
      double newBalance = balance - amount;
      setBalance(newBalance);
      DataSource.updateBalance(id, newBalance);
    }
  }

}
