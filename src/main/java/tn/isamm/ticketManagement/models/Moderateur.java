package tn.isamm.ticketManagement.models;

import java.util.ArrayList;
import java.util.List;

public class Moderateur {
    private String id;
    private String email;
    private String matricule;
    private String nom;
    private String telephone;
    private String adresse;
    private Specialite specialite;
    private final List<Ticket> tickets = new ArrayList<>();
    private final List<AffectationTicket> affectations = new ArrayList<>();

    public Moderateur() {
    }

    public Moderateur(String id, String email, String matricule, String nom,
                      String telephone, String adresse, Specialite specialite) {
        this.id = id;
        this.email = email;
        this.matricule = matricule;
        this.nom = nom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.specialite = specialite;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) { this.matricule = matricule; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public Specialite getSpecialite() { return specialite; }
    public void setSpecialite(Specialite specialite) { this.specialite = specialite; }
    public List<Ticket> getTickets() { return tickets; }
    public void addTicket(Ticket ticket) {
        if (ticket != null && !tickets.contains(ticket)) {
            tickets.add(ticket);
            ticket.setModerateur(this);
        }
    }
    public void removeTicket(Ticket ticket) {
        if (tickets.remove(ticket) && ticket.getModerateur() == this) ticket.setModerateur(null);
    }
    public List<AffectationTicket> getAffectations() { return affectations; }
    public void addAffectation(AffectationTicket affectation) {
        if (affectation != null && !affectations.contains(affectation)) {
            affectations.add(affectation);
            affectation.setModerateur(this);
        }
    }
}
