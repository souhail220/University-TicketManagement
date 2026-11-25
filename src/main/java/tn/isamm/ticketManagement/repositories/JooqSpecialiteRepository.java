package tn.isamm.ticketManagement.repositories;

import static tn.isamm.ticketManagement.configuration.DatabaseSchema.*;

import java.util.List;
import java.util.Optional;

import org.jooq.DSLContext;
import org.jooq.Record;

import tn.isamm.ticketManagement.configuration.Mapper;
import tn.isamm.ticketManagement.models.Specialite;
import tn.isamm.ticketManagement.repositories.RepoInterface.SpecialiteRepository;

public class JooqSpecialiteRepository implements SpecialiteRepository {
    private final DSLContext dsl;

    public JooqSpecialiteRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public Optional<Specialite> findById(String id) {
        Record record = dsl.select(SPECIALITE_ID, SPECIALITE_NOM)
                .from(SPECIALITE)
                .where(SPECIALITE_ID.eq(id))
                .fetchOne();
        return Optional.ofNullable(record).map(Mapper::toSpecialite);
    }

    @Override
    public List<Specialite> findAll() {
        return dsl.select(SPECIALITE_ID, SPECIALITE_NOM)
                .from(SPECIALITE)
                .fetch(Mapper::toSpecialite);
    }

    @Override
    public int insert(Specialite specialite) {
        return dsl.insertInto(SPECIALITE)
                .set(SPECIALITE_ID, specialite.getId())
                .set(SPECIALITE_NOM, specialite.getNom())
                .execute();
    }

    @Override
    public int update(Specialite specialite) {
        return dsl.update(SPECIALITE)
                .set(SPECIALITE_NOM, specialite.getNom())
                .where(SPECIALITE_ID.eq(specialite.getId()))
                .execute();
    }
}
