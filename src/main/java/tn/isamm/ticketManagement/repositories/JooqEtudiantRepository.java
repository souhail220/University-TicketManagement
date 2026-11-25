package tn.isamm.ticketManagement.repositories;

import static tn.isamm.ticketManagement.configuration.DatabaseSchema.*;

import java.util.List;
import java.util.Optional;

import org.jooq.DSLContext;
import org.jooq.Record;

import tn.isamm.ticketManagement.configuration.Mapper;
import tn.isamm.ticketManagement.models.Etudiant;
import tn.isamm.ticketManagement.repositories.RepoInterface.EtudiantRepository;

public class JooqEtudiantRepository implements EtudiantRepository {
    private final DSLContext dsl;

    public JooqEtudiantRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public Optional<Etudiant> findById(String id) {
        Record record = dsl.select(ETUDIANT_ID, ETUDIANT_EMAIL, ETUDIANT_NOM,
                        ETUDIANT_TELEPHONE, ETUDIANT_DATE_NAISSANCE, ETUDIANT_ADRESSE)
                .from(ETUDIANT)
                .where(ETUDIANT_ID.eq(id))
                .fetchOne();
        return Optional.ofNullable(record).map(Mapper::toStudent);
    }

    @Override
    public List<Etudiant> findAll() {
        return dsl.select(ETUDIANT_ID, ETUDIANT_EMAIL, ETUDIANT_NOM,
                        ETUDIANT_TELEPHONE, ETUDIANT_DATE_NAISSANCE, ETUDIANT_ADRESSE)
                .from(ETUDIANT)
                .fetch(Mapper::toStudent);
    }

    @Override
    public int insert(Etudiant etudiant) {
        return dsl.insertInto(ETUDIANT)
                .set(ETUDIANT_ID, etudiant.getId())
                .set(ETUDIANT_EMAIL, etudiant.getEmail())
                .set(ETUDIANT_NOM, etudiant.getNom())
                .set(ETUDIANT_TELEPHONE, etudiant.getTelephone())
                .set(ETUDIANT_DATE_NAISSANCE, etudiant.getDateNaissance())
                .set(ETUDIANT_ADRESSE, etudiant.getAdresse())
                .set(ETUDIANT_FILIERE_ID, etudiant.getFiliere() == null ? null : etudiant.getFiliere().getId())
                .execute();
    }

    @Override
    public int update(Etudiant etudiant) {
        return dsl.update(ETUDIANT)
                .set(ETUDIANT_EMAIL, etudiant.getEmail())
                .set(ETUDIANT_NOM, etudiant.getNom())
                .set(ETUDIANT_TELEPHONE, etudiant.getTelephone())
                .set(ETUDIANT_DATE_NAISSANCE, etudiant.getDateNaissance())
                .set(ETUDIANT_ADRESSE, etudiant.getAdresse())
                .set(ETUDIANT_FILIERE_ID, etudiant.getFiliere() == null ? null : etudiant.getFiliere().getId())
                .where(ETUDIANT_ID.eq(etudiant.getId()))
                .execute();
    }
}
