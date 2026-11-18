package tn.isamm.ticketManagement;

import java.sql.Connection;
import java.sql.SQLException;

import tn.isamm.ticketManagement.configuration.JdbcConfig;
import tn.isamm.ticketManagement.scripts.FetchTablesData;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("================================");
        System.out.println("    TICKET MANAGEMENT SYSTEM");
        System.out.println("================================");

        System.out.println("Application started successfully!");

        try (Connection connection = JdbcConfig.getConnection()) {

            System.out.println("PostgreSQL connected!");
            System.out.println(connection.getMetaData().getDatabaseProductVersion());

        } catch (SQLException e) {

            System.out.println("Database connection failed!");
            e.printStackTrace();
        }


        // Scripts
        try{

            // Create Table Script
            //CreateTables.createTables();

            // Fetch data scripts
            FetchTablesData.fetchTables();

        }catch(Exception e){
            System.out.println("Table creation failed!");
            e.printStackTrace();
        }
    }
}
