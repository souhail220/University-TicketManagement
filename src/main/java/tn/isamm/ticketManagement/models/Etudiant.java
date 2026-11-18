package tn.isamm.ticketManagement.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Etudiant {
    private String id;
    private String email;
    private String nom;
    private String telephone;
    private LocalDate dateNaissance;
    private String adresse;
    private Filiere filiere;
    private final List<Ticket> tickets = new ArrayList<>();

    public Etudiant() {
    }

    public Etudiant(String id, String email, String nom, String telephone,
                    LocalDate dateNaissance, String adresse, Filiere filiere) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.filiere = filiere;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public Filiere getFiliere() { return filiere; }
    public void setFiliere(Filiere filiere) { this.filiere = filiere; }
    public List<Ticket> getTickets() { return tickets; }
    public void addTicket(Ticket ticket) {
        if (ticket != null && !tickets.contains(ticket)) {
            tickets.add(ticket);
            ticket.setEtudiant(this);
        }
    }
    public void removeTicket(Ticket ticket) {
        if (tickets.remove(ticket) && ticket.getEtudiant() == this) ticket.setEtudiant(null);
    }
}
