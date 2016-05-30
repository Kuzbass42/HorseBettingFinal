package servlets.races;

import common.DaoProvider;
import dao.interfaces.HorseDao;
import dao.interfaces.RaceDao;
import dao.interfaces.RaceLineDao;
import model.Horse;
import model.Race;
import model.RaceLine;
import model.view.RaceView;
import service.DateConverter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ZENIT on 27.05.2016.
 */
@WebServlet("/races/admin/edit")
public class RaceTableEdit extends HttpServlet{
    private RaceDao raceDao;
    private RaceLineDao raceLineDao;
    private HorseDao horseDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        raceDao = (RaceDao) DaoProvider.getDao(Race.class);
        raceLineDao = (RaceLineDao) DaoProvider.getDao(RaceLine.class);
        horseDao = (HorseDao) DaoProvider.getDao(Horse.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Boolean.parseBoolean(req.getParameter("newrace"))) {
            req.setAttribute("newrace", true);
        } else {
            int raceId = Integer.parseInt(req.getParameter("raceid"));
            req.setAttribute("winninghorselist", raceDao.getHorsesListByRaceId(raceId));
            req.setAttribute("race", raceDao.getRacebyId(raceId));
            req.setAttribute("newrace", false);
        }

        req.setAttribute("horselist", horseDao.getHorsesList());

        req.getRequestDispatcher("/races/admin/edit/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int raceId;

        if (Boolean.parseBoolean(req.getParameter("raceline"))) {
            if (Boolean.parseBoolean(req.getParameter("delete"))) {
                raceId = Integer.parseInt(req.getParameter("raceid"));
                int lineNum = Integer.parseInt(req.getParameter("linenum"));

                raceLineDao.delete(raceId, lineNum);

                forwardRacesEdit(req, resp, raceId);

            } else if (Boolean.parseBoolean(req.getParameter("addline"))) {
                raceId = Integer.parseInt(req.getParameter("raceid"));

                if (req.getParameter("horsename") != null) {
                    RaceLine raceLine = new RaceLine(raceId,
                            Integer.parseInt(req.getParameter("horsename")),
                            Double.parseDouble(req.getParameter("odd")));

                    raceLineDao.insert(raceLine);
                }

                forwardRacesEdit(req, resp, raceId);
            } else {
                raceId = Integer.parseInt(req.getParameter("raceid"));
                int lineNum = Integer.parseInt(req.getParameter("linenum"));

                RaceLine raceLine = RaceLine.newInstanceFromRaceLineView(raceLineDao.getRaceLinebyId(raceId, lineNum));

                if (!req.getParameter("horsename").equals("")) raceLine.setHorseId(Integer.parseInt(req.getParameter("horsename")));
                if (!req.getParameter("odd").equals("")) raceLine.setOdd(Double.parseDouble(req.getParameter("odd")));

                raceLineDao.update(raceLine);

                forwardRacesEdit(req, resp, raceId);
            }

        } else {
            if (Boolean.parseBoolean(req.getParameter("delete"))) {
                raceId = Integer.parseInt(req.getParameter("raceid"));

                raceDao.delete(raceId);

                forwardRaces(req, resp);
            } else if (Boolean.parseBoolean(req.getParameter("newrace"))) {
                Race race = new Race(req.getParameter("racename"),
                                     DateConverter.convertControlDateToDate(req.getParameter("racedate")));

                raceDao.insert(race);

                forwardRaces(req, resp);
            } else {
                raceId = Integer.parseInt(req.getParameter("raceid"));

                Race race = Race.newInstanceFromRaceView(raceDao.getRacebyId(raceId));

                if (!req.getParameter("racename").equals("")) race.setRaceName(req.getParameter("racename"));
                if (!req.getParameter("racedate").equals("")) race.setRaceDate(DateConverter.convertControlDateToDate(req.getParameter("racedate")));
                if (req.getParameter("winhorse") != null) race.setRaceWinner(Integer.parseInt(req.getParameter("winhorse")));

                raceDao.update(race);

                if (race.getRaceWinner() != 0) {
                    forwardRaces(req, resp);
                } else {
                    forwardRacesEdit(req, resp, raceId);
                }
            }
        }
    }

    private void forwardRacesEdit(HttpServletRequest req, HttpServletResponse resp, int raceId) throws ServletException, IOException {
        req.setAttribute("newrace", false);
        req.setAttribute("race", raceDao.getRacebyId(raceId));
        req.setAttribute("winninghorselist", raceDao.getHorsesListByRaceId(raceId));
        req.setAttribute("horselist", horseDao.getHorsesList());

        req.getRequestDispatcher("/races/admin/edit/index.jsp").forward(req, resp);
    };

    private void forwardRaces(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/races").forward(req, resp);
    };
}
