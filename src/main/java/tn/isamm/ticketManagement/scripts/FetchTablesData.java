package tn.isamm.ticketManagement.scripts;

import static tn.isamm.ticketManagement.configuration.DatabaseSchema.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jooq.DSLContext;

import tn.isamm.ticketManagement.configuration.JdbcConfig;
import tn.isamm.ticketManagement.configuration.Mapper;
import tn.isamm.ticketManagement.models.Etudiant;
import tn.isamm.ticketManagement.models.Moderateur;
import tn.isamm.ticketManagement.models.Specialite;
import tn.isamm.ticketManagement.models.Ticket;

public final class FetchTablesData {
    private FetchTablesData() {}

    public static void fetchTables() throws SQLException {
        try (Connection connection = JdbcConfig.getConnection()) {
            DSLContext dsl = JdbcConfig.createDslContext(connection);

            System.out.println("Tickets:");
            fetchTickets(dsl).forEach(ticket -> System.out.println(
                    ticket.getId() + " | " + ticket.getTitre() + " | " + ticket.getStatut()));

            System.out.println("Etudiants:");
            fetchStudents(dsl).forEach(student -> System.out.println(
                    student.getId() + " | " + student.getNom() + " | " + student.getEmail()));

            System.out.println("Moderateurs:");
            fetchModerators(dsl).forEach(moderator -> System.out.println(
                    moderator.getId() + " | " + moderator.getNom() + " | "
                            + (moderator.getSpecialite() == null ? "sans spécialité"
                                    : moderator.getSpecialite().getNom())));

            System.out.println("Specialites:");
            fetchSpecialites(dsl).forEach(specialite -> System.out.println(
                    specialite.getId() + " | " + specialite.getNom()));
        }
    }

    public static List<Ticket> fetchTickets(DSLContext dsl) {
        return dsl.select(TICKET_ID, TICKET_STATUT, TICKET_DATE_NAISSANCE, TICKET_TITRE,
                        TICKET_DESCRIPTION, TICKET_TAG)
                .from(TICKET)
                .fetch(Mapper::toTicket);
    }

    public static List<Etudiant> fetchStudents(DSLContext dsl) {
        return dsl.select(ETUDIANT_ID, ETUDIANT_EMAIL, ETUDIANT_NOM,
                        ETUDIANT_TELEPHONE, ETUDIANT_DATE_NAISSANCE, ETUDIANT_ADRESSE)
                .from(ETUDIANT)
                .fetch(Mapper::toStudent);
    }

    public static List<Moderateur> fetchModerators(DSLContext dsl) {
        Map<String, Specialite> specialitesById = fetchSpecialites(dsl).stream()
                .collect(Collectors.toMap(Specialite::getId, Function.identity()));

        return dsl.select(MODERATEUR_ID, MODERATEUR_EMAIL, MODERATEUR_MATRICULE,
                        MODERATEUR_NOM, MODERATEUR_TELEPHONE, MODERATEUR_ADRESSE,
                        MODERATEUR_SPECIALITE_ID)
                .from(MODERATEUR)
                .fetch(record -> Mapper.toModerator(record, specialitesById));
    }

    public static List<Specialite> fetchSpecialites(DSLContext dsl) {
        return dsl.select(SPECIALITE_ID, SPECIALITE_NOM)
                .from(SPECIALITE)
                .fetch(Mapper::toSpecialite);
    }
}
