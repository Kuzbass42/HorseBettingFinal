package common;

import dao.interfaces.Dao;
import dao.mysql.*;
import model.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZENIT on 23.05.2016.
 */
public class DaoProvider {
    private static Map<String, Dao> daoMap = new HashMap<>();

    public static void put(Class daoClass, Dao dao) {
        daoMap.put(daoClass.getSimpleName(), dao);
    }

    public static Dao getDao(Class daoClass) {
        return daoMap.get(daoClass.getSimpleName());
    }

    public static void initDao(DataSource ds) {
        put(User.class, new MySqlUserDao() {
            @Override
            public Connection getConnection() throws SQLException {
                return ds.getConnection();
            }
        });

        put(RaceLine.class, new MySqlRaceLineDao() {
            @Override
            public Connection getConnection() throws SQLException {
                return ds.getConnection();
            }
        });

        put(Race.class, new MySqlRaceDao() {
            @Override
            public Connection getConnection() throws SQLException {
                return ds.getConnection();
            }
        });

        put(Odd.class, new MySqlOddDao() {
            @Override
            public Connection getConnection() throws SQLException {
                return ds.getConnection();
            }
        });

        put(Horse.class, new MySqlHorseDao() {
            @Override
            public Connection getConnection() throws SQLException {
                return ds.getConnection();
            }
        });
    };


}
