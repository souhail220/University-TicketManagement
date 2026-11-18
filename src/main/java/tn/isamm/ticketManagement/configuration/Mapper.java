package tn.isamm.ticketManagement.configuration;

import java.util.Map;
import org.jooq.Record;

import tn.isamm.ticketManagement.models.Etudiant;
import tn.isamm.ticketManagement.models.Moderateur;
import tn.isamm.ticketManagement.models.Specialite;
import tn.isamm.ticketManagement.models.Ticket;

import static tn.isamm.ticketManagement.configuration.DatabaseSchema.*;

public final class Mapper {

    private Mapper() {
    }

    public static Ticket toTicket(Record record) {
        return new Ticket(record.get(TICKET_ID), record.get(TICKET_STATUT),
                record.get(TICKET_DATE_NAISSANCE), record.get(TICKET_TITRE),
                record.get(TICKET_DESCRIPTION), record.get(TICKET_TAG));
    }

    public static Etudiant toStudent(Record record) {
        return new Etudiant(record.get(ETUDIANT_ID), record.get(ETUDIANT_EMAIL),
                record.get(ETUDIANT_NOM), record.get(ETUDIANT_TELEPHONE),
                record.get(ETUDIANT_DATE_NAISSANCE), record.get(ETUDIANT_ADRESSE), null);
    }

    public static Moderateur toModerator(Record record, Map<String, Specialite> specialitesById) {
        String specialiteId = record.get(MODERATEUR_SPECIALITE_ID);
        return new Moderateur(record.get(MODERATEUR_ID), record.get(MODERATEUR_EMAIL),
                record.get(MODERATEUR_MATRICULE), record.get(MODERATEUR_NOM),
                record.get(MODERATEUR_TELEPHONE), record.get(MODERATEUR_ADRESSE),
                specialitesById.get(specialiteId));
    }

    public static Specialite toSpecialite(Record record) {
        return new Specialite(record.get(SPECIALITE_ID), record.get(SPECIALITE_NOM));
    }
    
}
