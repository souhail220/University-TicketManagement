package tn.isamm.ticketManagement.repositories;

import static tn.isamm.ticketManagement.configuration.DatabaseSchema.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jooq.DSLContext;
import org.jooq.Record;

import tn.isamm.ticketManagement.configuration.Mapper;
import tn.isamm.ticketManagement.models.Moderateur;
import tn.isamm.ticketManagement.models.Specialite;
import tn.isamm.ticketManagement.repositories.RepoInterface.ModerateurRepository;

/** jOOQ implementation of the moderator persistence contract. */
public class JooqModerateurRepository implements ModerateurRepository {
    private final DSLContext dsl;

    public JooqModerateurRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public Optional<Moderateur> findById(String id) {
        Record record = dsl.select(MODERATEUR_ID, MODERATEUR_EMAIL, MODERATEUR_MATRICULE,
                        MODERATEUR_NOM, MODERATEUR_TELEPHONE, MODERATEUR_ADRESSE,
                        MODERATEUR_SPECIALITE_ID)
                .from(MODERATEUR)
                .where(MODERATEUR_ID.eq(id))
                .fetchOne();
        if (record == null) return Optional.empty();

        return Optional.of(Mapper.toModerator(record, fetchSpecialitesById()));
    }

    @Override
    public List<Moderateur> findAll() {
        Map<String, Specialite> specialites = fetchSpecialitesById();
        return dsl.select(MODERATEUR_ID, MODERATEUR_EMAIL, MODERATEUR_MATRICULE,
                        MODERATEUR_NOM, MODERATEUR_TELEPHONE, MODERATEUR_ADRESSE,
                        MODERATEUR_SPECIALITE_ID)
                .from(MODERATEUR)
                .fetch(record -> Mapper.toModerator(record, specialites));
    }

    @Override
    public int insert(Moderateur moderateur) {
        return dsl.insertInto(MODERATEUR)
                .set(MODERATEUR_ID, moderateur.getId())
                .set(MODERATEUR_EMAIL, moderateur.getEmail())
                .set(MODERATEUR_MATRICULE, moderateur.getMatricule())
                .set(MODERATEUR_NOM, moderateur.getNom())
                .set(MODERATEUR_TELEPHONE, moderateur.getTelephone())
                .set(MODERATEUR_ADRESSE, moderateur.getAdresse())
                .set(MODERATEUR_SPECIALITE_ID, specialiteId(moderateur))
                .execute();
    }

    @Override
    public int update(Moderateur moderateur) {
        return dsl.update(MODERATEUR)
                .set(MODERATEUR_EMAIL, moderateur.getEmail())
                .set(MODERATEUR_MATRICULE, moderateur.getMatricule())
                .set(MODERATEUR_NOM, moderateur.getNom())
                .set(MODERATEUR_TELEPHONE, moderateur.getTelephone())
                .set(MODERATEUR_ADRESSE, moderateur.getAdresse())
                .set(MODERATEUR_SPECIALITE_ID, specialiteId(moderateur))
                .where(MODERATEUR_ID.eq(moderateur.getId()))
                .execute();
    }

    @Override
    public Map<String, Specialite> fetchSpecialitesById() {
        return dsl.select(SPECIALITE_ID, SPECIALITE_NOM)
                .from(SPECIALITE)
                .fetch(Mapper::toSpecialite)
                .stream()
                .collect(Collectors.toMap(Specialite::getId, Function.identity()));
    }

    public static String specialiteId(Moderateur moderateur) {
        return moderateur.getSpecialite() == null ? null : moderateur.getSpecialite().getId();
    }
}
