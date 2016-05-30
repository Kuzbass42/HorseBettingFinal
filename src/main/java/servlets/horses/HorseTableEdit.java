package servlets.horses;

import com.sun.org.apache.xpath.internal.operations.Bool;
import common.DaoProvider;
import dao.interfaces.HorseDao;
import model.Horse;
import model.view.HorseView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/horses/admin/edit")
public class HorseTableEdit extends HttpServlet {
    private HorseDao horseDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        horseDao = (HorseDao) DaoProvider.getDao(Horse.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Boolean.parseBoolean(req.getParameter("newHorse"))) {
            req.setAttribute("newHorse", true);
        } else {
            req.setAttribute("horse", horseDao.getHorsebyId(Integer.parseInt(req.getParameter("horseId"))));
            req.setAttribute("newHorse", false);
        }

        req.getRequestDispatcher("/horses/admin/edit/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Boolean.parseBoolean(req.getParameter("delete"))) {
            int horseId = Integer.parseInt(req.getParameter("horseId"));
            horseDao.delete(horseId);
        } else if (Boolean.parseBoolean(req.getParameter("newHorse"))) {
            Horse horse = new Horse(req.getParameter("horseName"), req.getParameter("color"));
            horseDao.insert(horse);
        } else {
            HorseView horseView = horseDao.getHorsebyId(Integer.parseInt(req.getParameter("horseId")));
            Horse horse = Horse.newInstanceFromHorseView(horseView);

            if (!req.getParameter("horseName").equals("")) horse.setHorseName(req.getParameter("horseName"));
            if (!req.getParameter("color").equals("")) horse.setColor(req.getParameter("color"));

            horseDao.update(horse);
        }

        resp.sendRedirect("/horses");
    }
}
