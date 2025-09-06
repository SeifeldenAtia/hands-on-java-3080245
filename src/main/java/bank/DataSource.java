package bank;

import java.sql.Connection;
import java.sql.DriverManager;
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

  public static void main(String[] args) {
    connect();
  }

}
