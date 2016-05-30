package dao.interfaces;

import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Created by ZENIT on 23.05.2016.
 */
@FunctionalInterface
public interface Dao {
    Logger log = Logger.getLogger(Dao.class);

    Connection getConnection() throws SQLException;

    default int executeUpdate(String sql) {
        int countRows;

        log.info("Processing SQL statement " + sql);

        try (final Connection conn = getConnection();
             final Statement st = conn.createStatement()) {

            countRows = st.executeUpdate(sql);

        } catch (SQLException e) {
            log.error("SQL statement error " + sql, e);
            throw  new RuntimeException(e);
        }

        return countRows;
    };
}
