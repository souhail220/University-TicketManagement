package tn.isamm.ticketManagement.scripts;

import tn.isamm.ticketManagement.models.Etudiant;
import tn.isamm.ticketManagement.repositories.RepoInterface.EtudiantRepository;

public final class InsertTablesData {

    private final EtudiantRepository etudiantRepository;

    public InsertTablesData(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    public int insertEtudiant(Etudiant etudiant) {
        return etudiantRepository.insert(etudiant);
    }
}
