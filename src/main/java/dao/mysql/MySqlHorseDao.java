package dao.mysql;

import dao.interfaces.HorseDao;
import model.Horse;
import model.view.HorseView;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by ZENIT on 23.05.2016.
 */
@FunctionalInterface
public interface MySqlHorseDao extends HorseDao {
    Logger log = Logger.getLogger(MySqlHorseDao.class);

    @Override
    default ArrayList<HorseView> getHorsesList() {
        ArrayList<HorseView> horseList = new ArrayList<>();
        String sql = "select * from horsetable";

        log.info("Processing SQL statement " + sql);

        try (final Connection conn = getConnection();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                horseList.add(new HorseView(rs.getInt("horseId"), rs.getString("horseName"), rs.getString("color")));
            }
        } catch (SQLException e) {
            log.error("SQL statement error " + sql, e);
            throw  new RuntimeException(e);
        }

        return horseList;
    };

    @Override
    default HorseView getHorsebyId(int id) {
        HorseView horseView = null;
        String sql = "SELECT * FROM horsetable WHERE horseId = " + id;

        log.info("Processing SQL statement " + sql);

        try (final Connection conn = getConnection();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                horseView = new HorseView(id, rs.getString("horseName"), rs.getString("color"));
            }
        } catch (SQLException e) {
            log.error("SQL statement error " + sql, e);
            throw  new RuntimeException(e);
        }

        return horseView;
    };

    @Override
    default int insert(Horse horse) {
        String sql = "INSERT INTO horsetable (horseName, color) VALUES ('"
                + horse.getHorseName() + "', '"
                + horse.getColor() + "')";

        return executeUpdate(sql);
    };

    @Override
    default int update(Horse horse) {
        String sql = "UPDATE horsetable SET horseName='" + horse.getHorseName()
                + "', color='" + horse.getColor()
                + "' WHERE horseId=" + horse.getHorseId();

        return executeUpdate(sql);
    };

    @Override
    default int delete(int horseId) {
        String sql = "DELETE FROM horsetable WHERE horseId=" + horseId;

        return executeUpdate(sql);
    };
}
