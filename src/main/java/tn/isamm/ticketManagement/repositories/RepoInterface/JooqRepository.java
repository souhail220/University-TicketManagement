package tn.isamm.ticketManagement.repositories.RepoInterface;

import java.util.List;
import java.util.Optional;

/** Shared persistence operations implemented by jOOQ-backed repositories. */
public interface JooqRepository<T> {
    Optional<T> findById(String id);

    List<T> findAll();

    int insert(T entity);

    int update(T entity);
}
