package tn.isamm.ticketManagement.repositories.RepoInterface;

import java.util.Optional;

import tn.isamm.ticketManagement.models.Etudiant;

public interface EtudiantRepository extends JooqRepository<Etudiant> {
    Optional<Etudiant> findById(long id);
}
