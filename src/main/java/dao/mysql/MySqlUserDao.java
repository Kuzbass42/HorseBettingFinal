package dao.mysql;

import dao.interfaces.UserDao;
import model.User;
import model.view.HorseView;
import model.view.UserView;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by ZENIT on 23.05.2016.
 */
@FunctionalInterface
public interface MySqlUserDao extends UserDao {
    Logger log = Logger.getLogger(MySqlUserDao.class);

    @Override
    default UserView getUserByLogin(String login) {
        UserView userView = null;
        String sql = "SELECT * FROM usertable WHERE login = '" + login + "'";

        log.info("Processing SQL statement " + sql);

        try (final Connection conn = getConnection();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                userView = new UserView(rs.getInt("userId"),
                                        rs.getString("userName"),
                                        login,
                                        rs.getString("password"),
                                        rs.getDouble("balance"),
                                        rs.getString("userRole"));
            }
        } catch (SQLException e) {
            log.error("SQL statement error " + sql, e);
            throw  new RuntimeException(e);
        }

        return userView;
    };

    @Override
    default ArrayList<UserView> getUsersList() {
        ArrayList<UserView> usersList = new ArrayList<>();
        String sql = "select * from usertable";

        log.info("Processing SQL statement " + sql);

        try (final Connection conn = getConnection();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                usersList.add(new UserView(rs.getInt("userId"),
                                           rs.getString("userName"),
                                           rs.getString("login"),
                                           rs.getString("password"),
                                           rs.getDouble("balance"),
                                           rs.getString("userRole")));
            }
        } catch (SQLException e) {
            log.error("SQL statement error " + sql, e);
            throw  new RuntimeException(e);
        }

        return usersList;
    };

    @Override
    default int insert(User user) {
        String sql = "INSERT INTO usertable (userName, login, password, balance, userRole) VALUES ('"
                + user.getUserName() + "', '"
                + user.getLogin() + "', '"
                + user.getPassword() + "', '"
                + user.getBalance() + "', '"
                + user.getUserRole() + "')";

        return executeUpdate(sql);
    };

    @Override
    default int update(User user) {
        String sql = "UPDATE usertable SET userName='" + user.getUserName()
                + "', login='" + user.getLogin()
                + "', password='" + user.getPassword()
                + "', balance='" + user.getBalance()
                + "', userRole='" + user.getUserRole()
                + "' WHERE userId=" + user.getUserId();

        return executeUpdate(sql);
    };

    @Override
    default int delete(int userId) {
        String sql = "DELETE FROM usertable WHERE userId=" + userId;

        return executeUpdate(sql);
    };

    @Override
    default void deposit(int userId, double amount) {
        String sql = "UPDATE usertable SET balance='" + amount
                        + "' WHERE userId=" + userId;

        executeUpdate(sql);
    };
}
