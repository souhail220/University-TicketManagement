package tn.isamm.ticketManagement.models;

import java.util.ArrayList;
import java.util.List;

public class Specialite {
    private String id;
    private String nom;
    private final List<Moderateur> moderateurs = new ArrayList<>();

    public Specialite() {
    }

    public Specialite(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public List<Moderateur> getModerateurs() { return moderateurs; }
    public void addModerateur(Moderateur moderateur) {
        if (moderateur != null && !moderateurs.contains(moderateur)) {
            moderateurs.add(moderateur);
            moderateur.setSpecialite(this);
        }
    }
    public void removeModerateur(Moderateur moderateur) {
        if (moderateurs.remove(moderateur) && moderateur.getSpecialite() == this) moderateur.setSpecialite(null);
    }
}
