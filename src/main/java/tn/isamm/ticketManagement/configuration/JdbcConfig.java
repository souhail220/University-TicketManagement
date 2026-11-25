package tn.isamm.ticketManagement.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DSL;

public final class JdbcConfig {
    private static final String URL = System.getenv().getOrDefault(
            "DB_URL", "jdbc:postgresql://localhost:5432/university_ticket_system");
    private static final String USER = System.getenv().getOrDefault("DB_USER", "postgres");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "220410");

    private JdbcConfig() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // JOOQ Config
    public static DSLContext createDslContext(Connection connection) {
        DefaultConfiguration configuration = new DefaultConfiguration();
        configuration.set(SQLDialect.POSTGRES);
        configuration.set(new DefaultConnectionProvider(connection));
        return DSL.using(configuration);
    }
}
