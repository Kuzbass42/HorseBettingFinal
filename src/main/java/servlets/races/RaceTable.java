package servlets.races;

import common.DaoProvider;
import dao.interfaces.RaceDao;
import dao.interfaces.UserDao;
import model.Race;
import model.User;
import model.view.RaceView;
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
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ZENIT on 26.05.2016.
 */
@WebServlet("/races")
public class RaceTable extends HttpServlet {
    RaceDao raceDao;
    UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        raceDao = (RaceDao) DaoProvider.getDao(Race.class);
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
            ArrayList<RaceView> raceList = raceDao.getRaceList(new Date(0L));
            req.setAttribute("raceList", raceList);
            req.getRequestDispatcher("/races/admin/index.jsp").forward(req, resp);
        } else {
            ArrayList<RaceView> raceList = raceDao.getRaceList(new Date());
            UserView userView =  userDao.getUserByLogin(principal.getName());
            req.setAttribute("raceList", raceList);
            req.setAttribute("user", userView);
            req.getRequestDispatcher("/races/index.jsp").forward(req, resp);
        }
    }
}
