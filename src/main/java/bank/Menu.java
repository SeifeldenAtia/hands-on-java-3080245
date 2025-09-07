package bank;

import java.sql.SQLException;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

import bank.exceptions.AmountException;

public class Menu {

  private Scanner scanner;

  public static void main(String[] args) {
    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);
    System.out.println("Welcome to BankName bank!");
    Customer customer = menu.AuthenticateUser();

    if (customer != null) {
      Account account = DataSource.geAccount(customer.getAccountId());
      menu.showMenu(customer, account);
    }

    menu.scanner.close();

  }

  private Customer AuthenticateUser() {
    System.out.println("Please enter your username");
    String username = scanner.next();
    System.out.println("Please enter your password");
    String password = scanner.next();

    Customer customer = null;
    try {
      customer = Authenticator.login(username, password);
    } catch (LoginException e) {
      System.out.println("An Error occurred: " + e.getMessage());
    }

    return customer;
  }

  private void showMenu(Customer customer, Account account) {
    int selection = 0;
    while (selection != 4 && customer.isAuthenticated()) {
      System.out.println("=================================================");
      System.out.println("please select one of the following operations: ");
      System.out.println("1: Deposit");
      System.out.println("2: Withdraw");
      System.out.println("3: Check Balance");
      System.out.println("4: Exit");
      System.out.println("=================================================");

      selection = scanner.nextInt();
      double amount = 0;
      switch (selection) {
        case 1:
          System.out.println("How much would you to deposit? ");
          amount = scanner.nextDouble();
          try {
            account.deposit(amount);
          } catch (AmountException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again!");
          }

          break;
        case 2:
          System.out.println("How much would you to withdraw? ");
          amount = scanner.nextDouble();
          try {
            account.withdraw(amount);
          } catch (AmountException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again!");
          }
          break;
        case 3:
          System.out.println("Current Balance: " + account.getBalance());
          break;
        case 4:
          Authenticator.logout(customer);
          System.out.println("Thank you for banking at BankName bank!");
          break;

        default:
          System.out.println("Invalid operation, please try again!");
          break;
      }
    }

  }

}
