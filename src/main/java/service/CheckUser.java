package service;

import common.DaoProvider;
import dao.interfaces.UserDao;
import model.User;
import model.view.UserView;

/**
 * Created by ZENIT on 26.05.2016.
 */
public class CheckUser {
    public static boolean isAdmin(String login) {
        UserDao userDao = (UserDao) DaoProvider.getDao(User.class);
        UserView userView = userDao.getUserByLogin(login);
        return userView != null && userView.getUserRole().equals(User.UserRole.ADMIN.toString());
    };

    public static boolean isUser(String login) {
        UserDao userDao = (UserDao) DaoProvider.getDao(User.class);
        UserView userView = userDao.getUserByLogin(login);
        return userView != null && userView.getUserRole().equals(User.UserRole.USER);
    };
}
