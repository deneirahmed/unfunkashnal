package ca.sait.gifreviewr.dataaccess;

import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;

/**
 * Opens JDBC Connection
 * @author Nick Hamnett
 * @version January 25, 2021
 */
public class DatabaseDriver {
    private static DataSource dataSource = null;

    public DatabaseDriver() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/db");
        } catch (NamingException e) {
            System.out.println(e);
        }
    }
    
    /**
     * Gets connection to database
     * @return Connection instance
     */
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}
