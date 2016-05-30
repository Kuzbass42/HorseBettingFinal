package service;

import org.apache.log4j.Logger;
import servlets.authentification.Authentification;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Localizator")
public class Localizator extends HttpServlet{
    private static final Logger log = Logger.getLogger(Localizator.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String local = req.getParameter("local");
        setLocale(req.getSession(true), local);

        resp.sendRedirect("/");
    }

    public static void setLocale(HttpSession session, String local){
        String curLocal = (String) session.getAttribute("local");

        if (curLocal == null || !curLocal.equals(local)) {
            session.setAttribute("local", local);
            log.info("locale set to " + local);
        }
    }
}
