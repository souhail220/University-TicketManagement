package tn.isamm.ticketManagement;

import java.sql.Connection;
import java.sql.SQLException;

import org.jooq.DSLContext;

import tn.isamm.ticketManagement.configuration.JdbcConfig;
import tn.isamm.ticketManagement.scripts.FetchTablesData;
import tn.isamm.ticketManagement.scripts.InsertTablesData;
import tn.isamm.ticketManagement.repositories.JooqEtudiantRepository;
import tn.isamm.ticketManagement.repositories.JooqModerateurRepository;
import tn.isamm.ticketManagement.repositories.JooqSpecialiteRepository;
import tn.isamm.ticketManagement.repositories.JooqTicketRepository;

public class App {
    public static void main(String[] args) {
        System.out.println("================================");
        System.out.println("TICKET MANAGEMENT SYSTEM");
        System.out.println("================================");

        System.out.println("Application started successfully!");
        try (Connection connection = JdbcConfig.getConnection()) {
            System.out.println("PostgreSQL connected!");
            System.out.println(connection.getMetaData().getDatabaseProductVersion());

            DSLContext dsl = JdbcConfig.createDslContext(connection);

            try {
                // Fetch Data
                FetchTablesData fetcher = new FetchTablesData(
                        new JooqTicketRepository(dsl),
                        new JooqEtudiantRepository(dsl),
                        new JooqModerateurRepository(dsl),
                        new JooqSpecialiteRepository(dsl)
                );
                fetcher.fetchTables();

                // Insert Data
                InsertTablesData insertTablesData = new InsertTablesData(
                        new JooqEtudiantRepository(dsl)
                );
                //insertTablesData.InsertEtudiant(args);
            } catch (Exception e) {
                System.out.println("Error Occurred while querying the DB: " + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        } finally {
            System.out.println("Application finished successfully");
        }
    }
}
