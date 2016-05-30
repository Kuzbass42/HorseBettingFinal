package servlets.races;

import common.DaoProvider;
import dao.interfaces.OddDao;
import dao.interfaces.UserDao;
import model.Odd;
import model.User;
import model.view.UserView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/placebet")
public class PlaceBet extends HttpServlet{
    UserDao userDao;
    OddDao oddDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userDao = (UserDao) DaoProvider.getDao(User.class);
        oddDao = (OddDao) DaoProvider.getDao(Odd.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserView userView = userDao.getUserByLogin(req.getUserPrincipal().getName());

        if (req.getParameter("bet") != null) {
            if (Double.compare(userView.getBalance(), Double.parseDouble(req.getParameter("bet"))) < 0) {

                req.setAttribute("lowbalance", true);
                req.getRequestDispatcher("/users").forward(req, resp);
            } else {
                oddDao.insert(new Odd(new Date(),
                        userView.getUserId(),
                        Double.parseDouble(req.getParameter("bet")),
                        Integer.parseInt(req.getParameter("raceid")),
                        Integer.parseInt(req.getParameter("linenum"))));

                req.getRequestDispatcher("/odds").forward(req, resp);
            }
        } else {
            resp.sendRedirect("/");
        }
    }
}
