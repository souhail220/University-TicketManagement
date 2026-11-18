package tn.isamm.ticketManagement.repositories;

import static tn.isamm.ticketManagement.configuration.DatabaseSchema.*;

import java.util.List;
import java.util.Optional;

import org.jooq.DSLContext;
import org.jooq.Record;

import tn.isamm.ticketManagement.models.Ticket;

/** jOOQ operations for the existing PostgreSQL ticket table. */
public class TicketRepository {
    private final DSLContext dsl;

    public TicketRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Optional<Ticket> findById(String id) {
        Record record = dsl.select(TICKET_ID, TICKET_STATUT, TICKET_DATE_NAISSANCE,
                        TICKET_TITRE, TICKET_DESCRIPTION, TICKET_TAG)
                .from(TICKET)
                .where(TICKET_ID.eq(id))
                .fetchOne();
        return Optional.ofNullable(record).map(TicketRepository::toTicket);
    }

    public List<Ticket> findAll() {
        return dsl.select(TICKET_ID, TICKET_STATUT, TICKET_DATE_NAISSANCE,
                        TICKET_TITRE, TICKET_DESCRIPTION, TICKET_TAG)
                .from(TICKET)
                .fetch(TicketRepository::toTicket);
    }

    public int insert(Ticket ticket) {
        return dsl.insertInto(TICKET)
                .set(TICKET_ID, ticket.getId())
                .set(TICKET_STATUT, ticket.getStatut())
                .set(TICKET_DATE_NAISSANCE, ticket.getDateNaissance())
                .set(TICKET_TITRE, ticket.getTitre())
                .set(TICKET_DESCRIPTION, ticket.getDescription())
                .set(TICKET_TAG, ticket.getTag())
                .execute();
    }

    private static Ticket toTicket(Record record) {
        return new Ticket(
                record.get(TICKET_ID),
                record.get(TICKET_STATUT),
                record.get(TICKET_DATE_NAISSANCE),
                record.get(TICKET_TITRE),
                record.get(TICKET_DESCRIPTION),
                record.get(TICKET_TAG));
    }
}
