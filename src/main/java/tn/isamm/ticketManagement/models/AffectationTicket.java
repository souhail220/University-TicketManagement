package tn.isamm.ticketManagement.models;

import java.time.LocalDate;

public class AffectationTicket {
    private String id;
    private LocalDate affecteA;
    private String raison;
    private String affectationStatus;
    private Ticket ticket;
    private Moderateur moderateur;

    public AffectationTicket() {
    }

    public AffectationTicket(String id, LocalDate affecteA, String raison,
                             String affectationStatus, Ticket ticket, Moderateur moderateur) {
        this.id = id;
        this.affecteA = affecteA;
        this.raison = raison;
        this.affectationStatus = affectationStatus;
        this.ticket = ticket;
        this.moderateur = moderateur;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public LocalDate getAffecteA() { return affecteA; }
    public void setAffecteA(LocalDate affecteA) { this.affecteA = affecteA; }
    public String getRaison() { return raison; }
    public void setRaison(String raison) { this.raison = raison; }
    public String getAffectationStatus() { return affectationStatus; }
    public void setAffectationStatus(String affectationStatus) { this.affectationStatus = affectationStatus; }
    public Ticket getTicket() { return ticket; }
    public void setTicket(Ticket ticket) { this.ticket = ticket; }
    public Moderateur getModerateur() { return moderateur; }
    public void setModerateur(Moderateur moderateur) { this.moderateur = moderateur; }
}
