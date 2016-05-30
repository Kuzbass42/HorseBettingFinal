package dao.mysql;

import common.DaoProvider;
import dao.interfaces.HorseDao;
import dao.interfaces.RaceDao;
import model.Horse;
import model.Race;
import model.view.HorseView;
import model.view.RaceLineView;
import model.view.RaceView;
import org.apache.log4j.Logger;
import service.DateConverter;
import service.RaceViewComparatorByRaceDate;

import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ZENIT on 23.05.2016.
 */
@FunctionalInterface
public interface MySqlRaceDao extends RaceDao{
    Logger log = Logger.getLogger(MySqlRaceDao.class);

    @Override
    default ArrayList<RaceView> getRaceList(java.util.Date fromDate) {
        ArrayList<RaceView> raceList;
        Map<Integer, RaceView> raceMap = new HashMap<>();
        HorseView horseWinnerView;

        String sql = "SELECT * FROM racetable r "
                            + "left outer join raceline rl on r.raceId = rl.raceId "
                            + "left OUTER join horsetable h on  rl.horseId = h.horseId WHERE r.raceDate >= '"
                            + new java.sql.Date(fromDate.getTime()) + "'";//" ORDER BY r.raceDate";

        log.info("Processing SQL statement " + sql);

        //String sql = "SELECT * FROM racetable r, raceline rl, horsetable h WHERE raceDate >= '"
        //        + new java.sql.Date(fromDate.getTime()) + "' AND r.raceId = rl.raceId AND rl.horseId = h.horseId";

        try (final Connection conn = getConnection();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                HorseView horseView = null;
                RaceLineView raceLineView = null;
                int raceId = rs.getInt("r.raceId");

                if (rs.getInt("rl.raceId") != 0) {
                    horseView = new HorseView(rs.getInt("h.horseId"),
                            rs.getString("h.horseName"),
                            rs.getString("h.color"));

                    raceLineView = new RaceLineView(rs.getInt("rl.raceId"),
                            rs.getInt("rl.lineNum"),
                            horseView,
                            rs.getDouble("rl.odd"));
                }

                if (raceMap.containsKey(raceId)) {
                    RaceView raceView = raceMap.get(raceLineView.getRaceId());
                    raceView.addRaceLine(raceLineView);
                } else {
                    if (rs.getInt("r.raceWinner") != 0) {
                        HorseDao horseDao = (HorseDao) DaoProvider.getDao(Horse.class);
                        horseWinnerView = horseDao.getHorsebyId(rs.getInt("r.raceWinner"));
                    } else {
                        horseWinnerView = new HorseView(0, "", "");
                    }

                    RaceView raceView = new RaceView(rs.getInt("r.raceId"),
                                                     rs.getString("r.raceName"),
                                                     DateConverter.convertSqlDateToDate(rs.getString("raceDate")),
                                                     horseWinnerView,
                                                     new ArrayList<>());

                    if (raceLineView != null) {
                        raceView.addRaceLine(raceLineView);
                    }

                    raceMap.put(raceView.getRaceId(), raceView);
                }
            }

            raceList = new ArrayList<>(raceMap.values());
            Collections.sort(raceList, new RaceViewComparatorByRaceDate());

        } catch (SQLException e) {
            log.error("SQL statement error " + sql, e);
            throw  new RuntimeException(e);
        }

        return raceList;
    };

    @Override
    default RaceView getRacebyId(int id) {
        RaceView raceView = null;
        HorseView horseWinnerView;

        String sql = "SELECT * FROM racetable r "
                + "LEFT OUTER JOIN raceline rl on r.raceId = rl.raceId "
                + "LEFT OUTER JOIN horsetable h on  rl.horseId = h.horseId "
                + "WHERE r.raceId="+ id;

        log.info("Processing SQL statement " + sql);

        //String sql = "SELECT * FROM racetable r, raceline rl, horsetable h WHERE r.raceId = "
        //        + id + " AND r.raceId = rl.raceId AND rl.horseId = h.horseId";

        try (final Connection conn = getConnection();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                HorseView horseView = null;
                RaceLineView raceLineView = null;
                int raceId = rs.getInt("r.raceId");

                if (rs.getInt("rl.raceId") != 0) {
                    horseView = new HorseView(rs.getInt("h.horseId"),
                            rs.getString("h.horseName"),
                            rs.getString("h.color"));

                    raceLineView = new RaceLineView(rs.getInt("rl.raceId"),
                            rs.getInt("rl.lineNum"),
                            horseView,
                            rs.getDouble("rl.odd"));
                }

                if (raceView == null) {
                    if (rs.getInt("r.raceWinner") != 0) {
                        HorseDao horseDao = (HorseDao) DaoProvider.getDao(Horse.class);
                        horseWinnerView = horseDao.getHorsebyId(rs.getInt("r.raceWinner"));
                    } else {
                        horseWinnerView = new HorseView(0, "", "");
                    }

                    raceView = new RaceView(rs.getInt("r.raceId"),
                            rs.getString("r.raceName"),
                            DateConverter.convertSqlDateToDate(rs.getString("raceDate")),
                            horseWinnerView,
                            new ArrayList<>());
                }

                if (raceLineView != null) {
                    raceView.addRaceLine(raceLineView);
                }
            }
        } catch (SQLException e) {
            log.error("SQL statement error " + sql, e);
            throw  new RuntimeException(e);
        }

        return raceView;
    };

    @Override
    default ArrayList<HorseView> getHorsesListByRaceId(int raceId) {
        ArrayList<HorseView> horseList = new ArrayList<>();

        String sql = "SELECT * FROM horsetable h, raceline rl WHERE rl.horseId = h.horseId AND rl.raceId = " + raceId;

        log.info("Processing SQL statement " + sql);

        try (final Connection conn = getConnection();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                horseList.add(new HorseView(rs.getInt("h.horseId"), rs.getString("h.horseName"), rs.getString("h.color")));
            }
        } catch (SQLException e) {
            log.error("SQL statement error " + sql, e);
            throw  new RuntimeException(e);
        }

        return horseList;
    };

    @Override
    default int insert(Race race) {
        String sql = "INSERT INTO racetable (raceName, raceDate, raceWinner) VALUES ('"
                + race.getRaceName() + "', '"
                + DateConverter.converdDatetoStr(race.getRaceDate()) + "', NULL)";

        return executeUpdate(sql);
    };

    @Override
    default int update(Race race) {
        String sql = "UPDATE racetable SET raceName='" + race.getRaceName()
                + "', raceDate='" + DateConverter.converdDatetoStr(race.getRaceDate())
                + (race.getRaceWinner() == 0 ? "" : "', raceWinner='" + race.getRaceWinner())
                + "' WHERE raceId=" + race.getRaceId();

        return executeUpdate(sql);
    };

    @Override
    default int delete(int raceId) {
        String sql = "DELETE FROM racetable WHERE raceId=" + raceId;

        return executeUpdate(sql);
    };
}