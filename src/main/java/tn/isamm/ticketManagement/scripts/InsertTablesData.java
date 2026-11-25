package tn.isamm.ticketManagement.scripts;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

import tn.isamm.ticketManagement.configuration.JdbcConfig;
import tn.isamm.ticketManagement.models.Etudiant;
import tn.isamm.ticketManagement.repositories.RepoInterface.EtudiantRepository;

public final class InsertTablesData {

    private final EtudiantRepository etudiantRepository;

    public InsertTablesData(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    public void InsertEtudiant(String[] args) {
        try (Connection connection = JdbcConfig.getConnection()) {
            //DSLContext dsl = JdbcConfig.createDslContext(connection);
            //EtudiantRepository repository = new JooqEtudiantRepository(dsl);

            Etudiant etudiant = new Etudiant(
                    UUID.randomUUID().toString(),
                    "student@example.com",
                    "Sample Student",
                    "20000000",
                    LocalDate.of(2002, 5, 15),
                    "University Campus",
                    null);

            int insertedRows = etudiantRepository.insert(etudiant);
            System.out.println(insertedRows == 1
                    ? "Student inserted with id: " + etudiant.getId()
                    : "No student row was inserted.");
        } catch (SQLException e) {
            System.err.println("Could not insert student data.");
            e.printStackTrace();
        }
    }
}
