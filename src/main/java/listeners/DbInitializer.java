package listeners;

import common.DaoProvider;
import dao.mysql.*;
import model.*;
import org.apache.log4j.Logger;
import servlets.authentification.Authentification;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by ZENIT on 23.05.2016.
 */
@WebListener
public class DbInitializer implements ServletContextListener {
    private static final Logger log = Logger.getLogger(DbInitializer.class);

    @Resource(name="jdbc/ProdDB")
    private static DataSource ds;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("server started");
        DaoProvider.initDao(ds);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("server stopped");
    }
}
