package bank;

public class Account {

  private int id;
  private TYPE type;
  private double balance;

  public Account(int id, TYPE type, double balance) {
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

  public TYPE getType() {
    return this.type;
  }

  public void setType(TYPE type) {
    this.type = type;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

}
