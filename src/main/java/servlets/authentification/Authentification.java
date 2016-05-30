package servlets.authentification;

import common.DaoProvider;
import dao.interfaces.UserDao;
import model.User;
import model.view.UserView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@WebServlet("/auth")
public class Authentification extends HttpServlet{
    private static final Logger log = Logger.getLogger(Authentification.class);
    UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userDao = (UserDao) DaoProvider.getDao(User.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Boolean.parseBoolean(req.getParameter("login"))) {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/reg.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Boolean.parseBoolean(req.getParameter("logout"))) {
            try {
                req.logout();
                //req.getSession().invalidate();
                log.info("logged out");
            }catch (ServletException e) {
                log.error("Log out error", e);
                new RuntimeException(e);
            }
            resp.sendRedirect("/");
        } else if (Boolean.parseBoolean(req.getParameter("login"))) {
            if (req.getUserPrincipal() == null) {
                try {
                    req.login(req.getParameter("j_username"), req.getParameter("j_password"));
                    log.info(req.getParameter("j_username") + " logged in successful");
                } catch (ServletException e) {
                    log.error("Log in error", e);
                    new RuntimeException(e);
                }

                if (req.getUserPrincipal() == null) {
                    resp.sendRedirect("/error.jsp");
                } else {
                    resp.sendRedirect("/");
                }

            }
        } else if (Boolean.parseBoolean(req.getParameter("registered"))) {
            String userName = req.getParameter("userName");
            String login = req.getParameter("j_username");
            String password = req.getParameter("j_password");

            log.info(userName + " try to register");

            if (userName.isEmpty() || (login.isEmpty() || userDao.getUserByLogin(login) != null) || password.isEmpty()) {
                req.setAttribute("username", userName);
                req.setAttribute("j_username", login);
                req.setAttribute("j_password", password);

                req.setAttribute("error", true);

                log.info(userName + " registration error (blank fields)");

                req.getRequestDispatcher("/reg.jsp").forward(req, resp);
            } else {
                User user = new User(userName,
                                     login,
                                     password,
                                     User.UserRole.USER.toString());

                userDao.insert(user);

                try {
                    req.login(req.getParameter("j_username"), req.getParameter("j_password"));
                    log.info(req.getParameter("j_username") + " logged in successful");
                } catch (ServletException e) {
                    log.error("Log in error", e);
                    new RuntimeException(e);
                }

                if (req.getUserPrincipal() == null) {
                    resp.sendRedirect("/error.jsp");
                } else {
                    resp.sendRedirect("/");
                }
            }
        }
    }
}
