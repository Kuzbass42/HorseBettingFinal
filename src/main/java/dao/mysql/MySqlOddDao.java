package dao.mysql;

import common.DaoProvider;
import dao.interfaces.HorseDao;
import dao.interfaces.OddDao;
import model.Horse;
import model.Odd;
import model.view.*;
import org.apache.log4j.Logger;
import service.DateConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by ZENIT on 23.05.2016.
 */
@FunctionalInterface
public interface MySqlOddDao extends OddDao {
    Logger log = Logger.getLogger(MySqlOddDao.class);

    @Override
    default ArrayList<OddView> getOddList() {
        HorseView horseWinnerView = null;
        ArrayList<OddView> oddList = new ArrayList<>();

        String sql = "SELECT * FROM oddtable o, racetable r, raceline rl, horsetable h, usertable u WHERE "
                + "r.raceId = o.raceId AND rl.raceId = r.raceId AND rl.lineNum = o.lineNum AND h.horseId = rl.horseId "
                + "AND u.userId = o.userId ORDER BY o.transDate";

        log.info("Processing SQL statement " + sql);

        try (final Connection conn = getConnection();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                HorseView horseView = new HorseView(rs.getInt("h.horseId"),
                        rs.getString("h.horseName"),
                        rs.getString("h.color"));

                RaceLineView raceLineView = new RaceLineView(rs.getInt("rl.raceId"),
                        rs.getInt("rl.lineNum"),
                        horseView,
                        rs.getDouble("rl.odd"));

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
                        new ArrayList<RaceLineView>());
                raceView.addRaceLine(raceLineView);

                UserView userView = new UserView(rs.getInt("u.userId"),
                                                 rs.getString("u.userName"),
                                                 rs.getString("u.login"),
                                                 rs.getString("u.password"),
                                                 rs.getDouble("u.balance"),
                                                 rs.getString("u.userRole"));

                oddList.add(new OddView(rs.getInt("o.transId"),
                                        DateConverter.convertSqlDateToDate(rs.getString("transDate")),
                                        userView,
                                        rs.getDouble("o.amount"),
                                        raceLineView,
                                        raceView));
            }
        } catch (SQLException e) {
            log.error("SQL statement error " + sql, e);
            throw  new RuntimeException(e);
        }

        return oddList;
    };

    @Override
    default ArrayList<OddView> getOddListByUser(int userId) {
        HorseView horseWinnerView = null;
        ArrayList<OddView> oddList = new ArrayList<>();

        String sql = "SELECT * FROM oddtable o, racetable r, raceline rl, horsetable h, usertable u WHERE "
                + "r.raceId = o.raceId AND rl.raceId = r.raceId AND rl.lineNum = o.lineNum AND h.horseId = rl.horseId "
                + "AND u.userId = o.userId AND u.userId = " + userId + " ORDER BY o.transDate";

        log.info("Processing SQL statement " + sql);

        try (final Connection conn = getConnection();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                HorseView horseView = new HorseView(rs.getInt("h.horseId"),
                        rs.getString("h.horseName"),
                        rs.getString("h.color"));

                RaceLineView raceLineView = new RaceLineView(rs.getInt("rl.raceId"),
                        rs.getInt("rl.lineNum"),
                        horseView,
                        rs.getDouble("rl.odd"));

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
                        new ArrayList<RaceLineView>());
                raceView.addRaceLine(raceLineView);

                UserView userView = new UserView(rs.getInt("u.userId"),
                        rs.getString("u.userName"),
                        rs.getString("u.login"),
                        rs.getString("u.password"),
                        rs.getDouble("u.balance"),
                        rs.getString("u.userRole"));

                oddList.add(new OddView(rs.getInt("o.transId"),
                        DateConverter.convertSqlDateToDate(rs.getString("transDate")),
                        userView,
                        rs.getDouble("o.amount"),
                        raceLineView,
                        raceView));
            }
        } catch (SQLException e) {
            log.error("SQL statement error " + sql, e);
            throw  new RuntimeException(e);
        }

        return oddList;
    };

    @Override
    default int insert(Odd odd) {
        String sql = "INSERT INTO oddtable (transDate, userId, amount, raceId, lineNum) VALUES ('"
                + DateConverter.converdDatetoStr(odd.getTransDate()) + "', '"
                + odd.getUserId() + "', '"
                + odd.getAmount() + "', '"
                + odd.getRaceId() + "', '"
                + odd.getLineNum() + "')";

        return executeUpdate(sql);
    };
}