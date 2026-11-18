package tn.isamm.ticketManagement.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private String id;
    private String statut;
    private LocalDate dateNaissance;
    private String titre;
    private String description;
    private String tag;
    private Etudiant etudiant;
    private TicketType ticketType;
    private Motif motif;
    private Moderateur moderateur;
    private final List<HistoriqueTicket> historique = new ArrayList<>();
    private final List<AffectationTicket> affectations = new ArrayList<>();

    public Ticket() {}

    public Ticket(String id, String statut, LocalDate dateNaissance, String titre,
                  String description, String tag) {
        this.id = id;
        this.statut = statut;
        this.dateNaissance = dateNaissance;
        this.titre = titre;
        this.description = description;
        this.tag = tag;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }
    public Etudiant getEtudiant() { return etudiant; }
    public void setEtudiant(Etudiant etudiant) { this.etudiant = etudiant; }
    public TicketType getTicketType() { return ticketType; }
    public void setTicketType(TicketType ticketType) { this.ticketType = ticketType; }
    public Motif getMotif() { return motif; }
    public void setMotif(Motif motif) { this.motif = motif; }
    public Moderateur getModerateur() { return moderateur; }
    public void setModerateur(Moderateur moderateur) { this.moderateur = moderateur; }
    public List<HistoriqueTicket> getHistorique() { return historique; }
    public void addHistorique(HistoriqueTicket entree) {
        if (entree != null && !historique.contains(entree)) {
            historique.add(entree);
            entree.setTicket(this);
        }
    }
    public void removeHistorique(HistoriqueTicket entree) {
        if (historique.remove(entree) && entree.getTicket() == this) entree.setTicket(null);
    }
    public List<AffectationTicket> getAffectations() { return affectations; }
    public void addAffectation(AffectationTicket affectation) {
        if (affectation != null && !affectations.contains(affectation)) {
            affectations.add(affectation);
            affectation.setTicket(this);
        }
    }
    public void removeAffectation(AffectationTicket affectation) {
        if (affectations.remove(affectation) && affectation.getTicket() == this) affectation.setTicket(null);
    }
}
