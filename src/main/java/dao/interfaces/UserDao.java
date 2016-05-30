package dao.interfaces;

import model.User;
import model.view.HorseView;
import model.view.UserView;

import java.util.ArrayList;

/**
 * Created by ZENIT on 23.05.2016.
 */
public interface UserDao extends Dao {
    UserView getUserByLogin (String login);
    ArrayList<UserView> getUsersList();

    int insert(User user);
    int update(User user);
    int delete(int userId);

    void deposit(int userId, double amount);
}
