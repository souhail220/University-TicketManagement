package tn.isamm.ticketManagement.scripts;

import static org.jooq.impl.DSL.primaryKey;
import static org.jooq.impl.DSL.foreignKey;

import java.sql.Connection;
import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.impl.SQLDataType;

import tn.isamm.ticketManagement.configuration.JdbcConfig;

public final class CreateTables {
    private CreateTables() {
    }

    public static void createTables() throws SQLException {
        createSpecialiteTable();
        createModeratorTable();
        createStudentTable();
        createTicketTable();
    }

    public static void createTicketTable() throws SQLException {
        try (Connection connection = JdbcConfig.getConnection()) {
            DSLContext dsl = JdbcConfig.createDslContext(connection);

            dsl.createTableIfNotExists("ticket")
                    .column("id", SQLDataType.VARCHAR(36).nullable(false))
                    .column("statut", SQLDataType.VARCHAR(30))
                    .column("dateNaissance", SQLDataType.DATE)
                    .column("titre", SQLDataType.VARCHAR(200))
                    .column("description", SQLDataType.CLOB)
                    .column("tag", SQLDataType.VARCHAR(100))
                    .column("etudiant_id", SQLDataType.BIGINT)
                    .constraints(primaryKey("id"))
                    .execute();

            // Also adds the relation when upgrading a database with an existing ticket table.
            dsl.execute("ALTER TABLE ticket ADD COLUMN IF NOT EXISTS etudiant_id BIGINT");
            dsl.execute("DO $$ BEGIN "
                    + "IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'ticket_etudiant_fk') THEN "
                    + "ALTER TABLE ticket ADD CONSTRAINT ticket_etudiant_fk "
                    + "FOREIGN KEY (etudiant_id) REFERENCES etudiant(id); "
                    + "END IF; END $$");

            System.out.println("Table ticket created or already exists.");
        }
    }

    public static void createStudentTable() throws SQLException {
        try (Connection connection = JdbcConfig.getConnection()) {
            DSLContext dsl = JdbcConfig.createDslContext(connection);

            dsl.createTableIfNotExists("etudiant")
                    .column("id", SQLDataType.BIGINT.nullable(false))
                    .column("email", SQLDataType.VARCHAR(255))
                    .column("nom", SQLDataType.VARCHAR(150))
                    .column("telephone", SQLDataType.VARCHAR(30))
                    .column("dateNaissance", SQLDataType.DATE)
                    .column("adresse", SQLDataType.VARCHAR(255))
                    .column("filiere_id", SQLDataType.VARCHAR(36))
                    .constraints(primaryKey("id"))
                    .execute();

            System.out.println("Table etudiant created or already exists.");
        }
    }

    public static void createModeratorTable() throws SQLException {
        try (Connection connection = JdbcConfig.getConnection()) {
            DSLContext dsl = JdbcConfig.createDslContext(connection);

            dsl.createTableIfNotExists("moderateur")
                    .column("id", SQLDataType.VARCHAR(36).nullable(false))
                    .column("email", SQLDataType.VARCHAR(255))
                    .column("matricule", SQLDataType.VARCHAR(50))
                    .column("nom", SQLDataType.VARCHAR(150))
                    .column("telephone", SQLDataType.VARCHAR(30))
                    .column("adresse", SQLDataType.VARCHAR(255))
                    .column("specialite_id", SQLDataType.VARCHAR(36))
                    .constraints(
                            primaryKey("id"),
                            foreignKey("specialite_id").references("specialite", "id"))
                    .execute();

            System.out.println("Table moderateur created or already exists.");
        }
    }

    public static void createSpecialiteTable() throws SQLException {
        try (Connection connection = JdbcConfig.getConnection()) {
            DSLContext dsl = JdbcConfig.createDslContext(connection);

            dsl.createTableIfNotExists("specialite")
                    .column("id", SQLDataType.VARCHAR(36).nullable(false))
                    .column("nom", SQLDataType.VARCHAR(150))
                    .constraints(primaryKey("id"))
                    .execute();

            System.out.println("Table specialite created or already exists.");
        }
    }
}
