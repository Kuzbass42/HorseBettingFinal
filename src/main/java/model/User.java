package model;

/**
 * Created by ZENIT on 23.05.2016.
 */
public class User {
    private int         userId;
    private String      userName;
    private String      login;
    private String      password;
    private double      balance;
    private String      userRole;

    public enum UserRole {
        USER, ADMIN
    }

    public User(String userName, String login, String password, String userRole) {
        this.userId = 0;
        this.userName = userName;
        this.login = login;
        this.password = password;
        this.balance = 0.00;
        this.userRole = userRole;
    }

    public User(int userId, String userName, String login, String password, double balance, String userRole) {
        this.userId = userId;
        this.userName = userName;
        this.login = login;
        this.password = password;
        this.balance = balance;
        this.userRole = userRole;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
