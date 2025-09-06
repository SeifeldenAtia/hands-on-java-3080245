package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {

  public static Connection connect() {
    String filePath = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;

    try {
      connection = DriverManager.getConnection(filePath);
      System.out.println("Connected to DB successfully!");
      return connection;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }

  public static Customer getCustomer(String username) {
    String query = "SELECT * FROM Customers WHERE USERNAME = ?";
    Customer customer = null;
    try (Connection connection = connect(); PreparedStatement statement = connection.prepareStatement(query)) {

      statement.setString(1, username);
      try (ResultSet resultSet = statement.executeQuery();) {
        customer = new Customer(
            resultSet.getInt("ID"),
            resultSet.getString("NAME"),
            resultSet.getString("USERNAME"),
            resultSet.getString("PASSWORD"),
            resultSet.getInt("ACCOUNT_ID"));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return customer;
  }

  public static Account geAccount(int id) {
    String query = "SELECT * FROM Accounts where id = ?";
    Account account = null;
    try (Connection connection = connect(); PreparedStatement statement = connection.prepareStatement(query)) {

      statement.setInt(1, id);
      try (ResultSet resultSet = statement.executeQuery();) {
        account = new Account(
            id,
            resultSet.getString("TYPE"),
            resultSet.getDouble("BALANCE"));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return account;

  }

  public static void main(String[] args) {
    Customer customer = getCustomer("lfromonte9@de.vu");
    System.out.println(customer.getName());
    Account account = geAccount(11963);
    System.out.println(account.getBalance());
  }

}
