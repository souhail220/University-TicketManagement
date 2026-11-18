package tn.isamm.ticketManagement.models;

import java.util.ArrayList;
import java.util.List;

public class Motif {
    private String id;
    private String nomMotif;
    private final List<Ticket> tickets = new ArrayList<>();

    public Motif() {
    }

    public Motif(String id, String nomMotif) {
        this.id = id;
        this.nomMotif = nomMotif;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNomMotif() { return nomMotif; }
    public void setNomMotif(String nomMotif) { this.nomMotif = nomMotif; }
    public List<Ticket> getTickets() { return tickets; }
    public void addTicket(Ticket ticket) {
        if (ticket != null && !tickets.contains(ticket)) {
            tickets.add(ticket);
            ticket.setMotif(this);
        }
    }
    public void removeTicket(Ticket ticket) {
        if (tickets.remove(ticket) && ticket.getMotif() == this) ticket.setMotif(null);
    }
}
