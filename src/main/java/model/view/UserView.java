package model.view;

/**
 * Created by ZENIT on 25.05.2016.
 */
public class UserView {
        private int         userId;
        private String      userName;
        private String      login;
        private String      password;
        private double      balance;
        private String      userRole;

    public UserView(int userId, String userName, String login, String password, double balance, String userRole) {
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
