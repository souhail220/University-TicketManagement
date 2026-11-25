package tn.isamm.ticketManagement.repositories;

import static tn.isamm.ticketManagement.configuration.DatabaseSchema.*;

import java.util.List;
import java.util.Optional;

import org.jooq.DSLContext;
import org.jooq.Record;

import tn.isamm.ticketManagement.configuration.Mapper;
import tn.isamm.ticketManagement.models.Ticket;
import tn.isamm.ticketManagement.repositories.RepoInterface.TicketRepository;

/** jOOQ implementation of the ticket persistence contract. */
public class JooqTicketRepository implements TicketRepository {
    private final DSLContext dsl;

    public JooqTicketRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public Optional<Ticket> findById(String id) {
        Record record = dsl.select(TICKET_ID, TICKET_STATUT, TICKET_DATE_NAISSANCE,
                        TICKET_TITRE, TICKET_DESCRIPTION, TICKET_TAG)
                .from(TICKET)
                .where(TICKET_ID.eq(id))
                .fetchOne();
        return Optional.ofNullable(record).map(Mapper::toTicket);
    }

    @Override
    public List<Ticket> findAll() {
        return dsl.select(TICKET_ID, TICKET_STATUT, TICKET_DATE_NAISSANCE,
                        TICKET_TITRE, TICKET_DESCRIPTION, TICKET_TAG)
                .from(TICKET)
                .fetch(Mapper::toTicket);
    }

    @Override
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

    @Override
    public int update(Ticket ticket) {
        return dsl.update(TICKET)
            .set(TICKET_STATUT, ticket.getStatut())
            .set(TICKET_DATE_NAISSANCE, ticket.getDateNaissance())
            .set(TICKET_TITRE, ticket.getTitre())
            .set(TICKET_DESCRIPTION, ticket.getDescription())
            .set(TICKET_TAG, ticket.getTag())
            .where(TICKET_ID.eq(ticket.getId()))
            .execute();
    }
}
