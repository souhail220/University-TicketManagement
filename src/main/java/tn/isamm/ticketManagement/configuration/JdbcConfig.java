package tn.isamm.ticketManagement.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/university_ticket_system";
    private static final String USER = "postgres";
    private static final String PASSWORD = "220410";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
