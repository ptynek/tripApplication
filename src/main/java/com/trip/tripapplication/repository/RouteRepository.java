package com.trip.tripapplication.repository;

import com.trip.tripapplication.domain.Route;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RouteRepository extends CrudRepository<Route, Long> {

    @Override
    Optional<Route> findById(final Long id);

    @Override
    Route save(final Route route);

    @Override
    List<Route> findAll();
}
