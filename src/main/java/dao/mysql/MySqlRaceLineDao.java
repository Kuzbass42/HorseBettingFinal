package dao.mysql;

import dao.interfaces.RaceLineDao;
import model.RaceLine;
import model.view.HorseView;
import model.view.RaceLineView;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZENIT on 23.05.2016.
 */
@FunctionalInterface
public interface MySqlRaceLineDao extends RaceLineDao{
    Logger log = Logger.getLogger(MySqlRaceLineDao.class);

    @Override
    default Map<Integer, RaceLine> getRaceLineMap() {
        Map<Integer, RaceLine> raceLineMap = new HashMap<>();
        String sql = "select * from raceline";

        log.info("Processing SQL statement " + sql);

        try (final Connection conn = getConnection();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                raceLineMap.put(rs.getInt("raceId"),
                                new RaceLine(rs.getInt("raceId"),
                                             rs.getInt("lineNum"),
                                             rs.getInt("horseId"),
                                             rs.getDouble("odd")));
            }
        } catch (SQLException e) {
            log.error("SQL statement error " + sql, e);
            throw  new RuntimeException(e);
        }

        return raceLineMap;
    };

    @Override
    default RaceLineView getRaceLinebyId(int raceId, int lineNum) {
        RaceLineView raceLineView = null;

        String sql = "SELECT * FROM raceline rl, horsetable h WHERE rl.horseId = h.horseId"
                + " AND rl.raceId= " + raceId + " AND rl.lineNum=" + lineNum;

        log.info("Processing SQL statement " + sql);

        try (final Connection conn = getConnection();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                HorseView horseView = new HorseView(rs.getInt("h.horseId"),
                                                    rs.getString("h.horseName"),
                                                    rs.getString("h.color"));

                raceLineView = new RaceLineView(rs.getInt("rl.raceId"),
                                                          rs.getInt("rl.lineNum"),
                                                          horseView,
                                                          rs.getDouble("rl.odd"));
            }
        } catch (SQLException e) {
            log.error("SQL statement error " + sql, e);
            throw  new RuntimeException(e);
        }

        return raceLineView;
    };

    @Override
    default int insert(RaceLine raceLine) {
        String sql = "INSERT INTO raceline (raceId, horseId, odd) VALUES ('"
                + raceLine.getRaceId() + "', '"
                + raceLine.getHorseId() + "', '"
                + raceLine.getOdd() + "')";

        return executeUpdate(sql);
    };

    @Override
    default int update(RaceLine raceLine) {
        String sql = "UPDATE raceline SET horseId='" + raceLine.getHorseId()
                + "', odd='" + raceLine.getOdd()
                + "' WHERE raceId=" + raceLine.getRaceId()
                + " AND lineNum=" + raceLine.getLineNum();

        return executeUpdate(sql);
    };

    @Override
    default int delete(int raceId, int lineNum) {
        String sql = "DELETE FROM raceline WHERE raceId=" + raceId + " AND lineNum=" + lineNum;

        return executeUpdate(sql);
    };
}
