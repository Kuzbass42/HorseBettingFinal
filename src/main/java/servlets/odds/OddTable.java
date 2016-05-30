package servlets.odds;

import common.DaoProvider;
import dao.interfaces.OddDao;
import dao.interfaces.RaceDao;
import dao.interfaces.UserDao;
import model.Odd;
import model.Race;
import model.User;
import model.view.OddView;
import model.view.RaceView;
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
import java.util.Date;

/**
 * Created by ZENIT on 26.05.2016.
 */
@WebServlet("/odds")
public class OddTable extends HttpServlet {
    OddDao oddDao;
    UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        oddDao = (OddDao) DaoProvider.getDao(Odd.class);
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
            ArrayList<OddView> oddList = oddDao.getOddList();
            req.setAttribute("oddList", oddList);
            req.getRequestDispatcher("/odds/admin/index.jsp").forward(req, resp);
        } else {
            ArrayList<OddView> oddList = oddDao.getOddListByUser(userDao.getUserByLogin(req.getUserPrincipal().getName()).getUserId());
            req.setAttribute("oddList", oddList);
            req.getRequestDispatcher("/odds/index.jsp").forward(req, resp);
        }
    }
}
