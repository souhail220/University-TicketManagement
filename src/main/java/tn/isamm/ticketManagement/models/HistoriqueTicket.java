package tn.isamm.ticketManagement.models;

import java.time.LocalDateTime;

public class HistoriqueTicket {
    private String id;
    private String statusAncient;
    private String statusNouveau;
    private LocalDateTime dateChange;
    private String commentaire;
    private Ticket ticket;

    public HistoriqueTicket() {
    }

    public HistoriqueTicket(String id, String statusAncient, String statusNouveau,
                            LocalDateTime dateChange, String commentaire, Ticket ticket) {
        this.id = id;
        this.statusAncient = statusAncient;
        this.statusNouveau = statusNouveau;
        this.dateChange = dateChange;
        this.commentaire = commentaire;
        this.ticket = ticket;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getStatusAncient() { return statusAncient; }
    public void setStatusAncient(String statusAncient) { this.statusAncient = statusAncient; }
    public String getStatusNouveau() { return statusNouveau; }
    public void setStatusNouveau(String statusNouveau) { this.statusNouveau = statusNouveau; }
    public LocalDateTime getDateChange() { return dateChange; }
    public void setDateChange(LocalDateTime dateChange) { this.dateChange = dateChange; }
    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }
    public Ticket getTicket() { return ticket; }
    public void setTicket(Ticket ticket) { this.ticket = ticket; }
}
