package tn.isamm.ticketManagement.models;

import java.util.ArrayList;
import java.util.List;

public class TicketType {
    private String id;
    private String type;
    private final List<Ticket> tickets = new ArrayList<>();

    public TicketType() {
    }

    public TicketType(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public List<Ticket> getTickets() { return tickets; }
    public void addTicket(Ticket ticket) {
        if (ticket != null && !tickets.contains(ticket)) {
            tickets.add(ticket);
            ticket.setTicketType(this);
        }
    }
    public void removeTicket(Ticket ticket) {
        if (tickets.remove(ticket) && ticket.getTicketType() == this) ticket.setTicketType(null);
    }
}
