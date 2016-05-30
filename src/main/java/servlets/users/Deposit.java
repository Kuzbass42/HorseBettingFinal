package servlets.users;

import common.DaoProvider;
import dao.interfaces.UserDao;
import model.User;
import model.view.UserView;
import service.CheckUser;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@WebServlet("/deposit")
public class Deposit extends HttpServlet {
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
        UserView userView = userDao.getUserByLogin(principal.getName());
        userDao.deposit(userView.getUserId(), userView.getBalance() + Double.parseDouble(req.getParameter("depamount")));
        req.setAttribute("user", userView);
        req.getRequestDispatcher("/users").forward(req, resp);
    }
}
