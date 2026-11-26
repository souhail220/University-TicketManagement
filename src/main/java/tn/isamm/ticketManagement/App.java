package tn.isamm.ticketManagement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jooq.DSLContext;
import tn.isamm.ticketManagement.configuration.JdbcConfig;
import tn.isamm.ticketManagement.scenario.CreateTicketScenario;
import tn.isamm.ticketManagement.repositories.JooqEtudiantRepository;
import tn.isamm.ticketManagement.repositories.JooqTicketRepository;
import tn.isamm.ticketManagement.scenario.StudentScenarios;
import tn.isamm.ticketManagement.scripts.InsertTablesData;

public class App {
    public static void main(String[] args) {
        System.out.println("================================");
        System.out.println("TICKET MANAGEMENT SYSTEM");
        System.out.println("================================");
        Logger LOGGER = Logger.getLogger(App.class.getName());

        System.out.println("Application started successfully!");
        try (Connection connection = JdbcConfig.getConnection()) {
            System.out.println("PostgreSQL connected!");
            System.out.println(connection.getMetaData().getDatabaseProductVersion());

            DSLContext dsl = JdbcConfig.createDslContext(connection);

            CreateTicketScenario ticketScenario = new CreateTicketScenario(
                    new JooqTicketRepository(dsl),
                    new JooqEtudiantRepository(dsl)
            );

            StudentScenarios studentScenarios = new StudentScenarios(
                    new InsertTablesData(new JooqEtudiantRepository(dsl))
            );

            try {
                ticketScenario.run();
            } catch (Exception e) {
                System.out.println("Error Occurred while querying the DB: " + e.getMessage());
                LOGGER.log(Level.SEVERE, "Operation failed", e);
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            LOGGER.log(Level.SEVERE, "Operation failed", e);
        } finally {
            System.out.println("Application finished successfully");
        }
    }
}
