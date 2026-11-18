package tn.isamm.ticketManagement.configuration;

import java.time.LocalDate;

import org.jooq.Field;
import org.jooq.Table;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;

/** Shared jOOQ table and column definitions for the existing database schema. */
public final class DatabaseSchema {
    public static final Table<?> TICKET = table(name("ticket"));
    public static final Field<String> TICKET_ID = field(name("ticket", "id"), String.class);
    public static final Field<String> TICKET_STATUT = field(name("ticket", "statut"), String.class);
    public static final Field<LocalDate> TICKET_DATE_NAISSANCE =
            field(name("ticket", "dateNaissance"), LocalDate.class);
    public static final Field<String> TICKET_TITRE = field(name("ticket", "titre"), String.class);
    public static final Field<String> TICKET_DESCRIPTION = field(name("ticket", "description"), String.class);
    public static final Field<String> TICKET_TAG = field(name("ticket", "tag"), String.class);

    public static final Table<?> ETUDIANT = table(name("etudiant"));
    public static final Field<String> ETUDIANT_ID = field(name("etudiant", "id"), String.class);
    public static final Field<String> ETUDIANT_EMAIL = field(name("etudiant", "email"), String.class);
    public static final Field<String> ETUDIANT_NOM = field(name("etudiant", "nom"), String.class);
    public static final Field<String> ETUDIANT_TELEPHONE = field(name("etudiant", "telephone"), String.class);
    public static final Field<LocalDate> ETUDIANT_DATE_NAISSANCE =
            field(name("etudiant", "dateNaissance"), LocalDate.class);
    public static final Field<String> ETUDIANT_ADRESSE = field(name("etudiant", "adresse"), String.class);
    public static final Field<String> ETUDIANT_FILIERE_ID = field(name("etudiant", "filiere_id"), String.class);

    public static final Table<?> MODERATEUR = table(name("moderateur"));
    public static final Field<String> MODERATEUR_ID = field(name("moderateur", "id"), String.class);
    public static final Field<String> MODERATEUR_EMAIL = field(name("moderateur", "email"), String.class);
    public static final Field<String> MODERATEUR_MATRICULE = field(name("moderateur", "matricule"), String.class);
    public static final Field<String> MODERATEUR_NOM = field(name("moderateur", "nom"), String.class);
    public static final Field<String> MODERATEUR_TELEPHONE = field(name("moderateur", "telephone"), String.class);
    public static final Field<String> MODERATEUR_ADRESSE = field(name("moderateur", "adresse"), String.class);
    public static final Field<String> MODERATEUR_SPECIALITE_ID =
            field(name("moderateur", "specialite_id"), String.class);

    public static final Table<?> SPECIALITE = table(name("specialite"));
    public static final Field<String> SPECIALITE_ID = field(name("specialite", "id"), String.class);
    public static final Field<String> SPECIALITE_NOM = field(name("specialite", "nom"), String.class);

    private DatabaseSchema() {
    }
}
