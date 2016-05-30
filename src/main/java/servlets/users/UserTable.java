package servlets.users;

import common.DaoProvider;
import dao.interfaces.HorseDao;
import dao.interfaces.RaceDao;
import dao.interfaces.UserDao;
import model.Horse;
import model.Race;
import model.User;
import model.view.HorseView;
import service.CheckUser;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;

@WebServlet("/users")
public class UserTable extends HttpServlet {
    UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userDao = (UserDao) DaoProvider.getDao(User.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Principal principal = req.getUserPrincipal();

        if (CheckUser.isAdmin(principal.getName())) {
            req.setAttribute("userlist", userDao.getUsersList());
            req.getRequestDispatcher("/users/admin/index.jsp").forward(req, resp);
        } else {
            req.setAttribute("user", userDao.getUserByLogin(principal.getName()));
            req.getRequestDispatcher("/users/index.jsp").forward(req, resp);
        }
    }
}
