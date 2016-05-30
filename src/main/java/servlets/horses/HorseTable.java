package servlets.horses;

import common.DaoProvider;
import dao.interfaces.HorseDao;
import dao.mysql.MySqlHorseDao;
import model.Horse;
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
import java.util.Collection;

/**
 * Created by ZENIT on 25.05.2016.
 */
@WebServlet("/horses")
public class HorseTable extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HorseDao horseDao = (HorseDao) DaoProvider.getDao(Horse.class);
        ArrayList<HorseView> horseList = horseDao.getHorsesList();
        req.setAttribute("horseList", horseList);

        Principal principal = req.getUserPrincipal();

        if (CheckUser.isAdmin(principal.getName())) {
            req.getRequestDispatcher("/horses/admin/index.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/horses/index.jsp").forward(req, resp);
        }
    }
}
