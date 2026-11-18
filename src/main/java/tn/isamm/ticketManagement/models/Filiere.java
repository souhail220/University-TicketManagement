package tn.isamm.ticketManagement.models;

import java.util.ArrayList;
import java.util.List;

public class Filiere {
    private String id;
    private String nomFiliere;
    private final List<Etudiant> etudiants = new ArrayList<>();

    public Filiere() {
    }

    public Filiere(String id, String nomFiliere) {
        this.id = id;
        this.nomFiliere = nomFiliere;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNomFiliere() { return nomFiliere; }
    public void setNomFiliere(String nomFiliere) { this.nomFiliere = nomFiliere; }
    public List<Etudiant> getEtudiants() { return etudiants; }
    public void addEtudiant(Etudiant etudiant) {
        if (etudiant != null && !etudiants.contains(etudiant)) {
            etudiants.add(etudiant);
            etudiant.setFiliere(this);
        }
    }
    public void removeEtudiant(Etudiant etudiant) {
        if (etudiants.remove(etudiant) && etudiant.getFiliere() == this) {
            etudiant.setFiliere(null);
        }
    }
}
