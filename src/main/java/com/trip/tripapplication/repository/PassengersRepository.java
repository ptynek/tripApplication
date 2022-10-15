package com.trip.tripapplication.repository;

import com.trip.tripapplication.domain.Passengers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface PassengersRepository extends CrudRepository<Passengers, Long> {

    @Override
    Optional<Passengers> findById(Long id);

    @Override
    List<Passengers> findAll();

    @Override
    Passengers save(final Passengers passengers);
}
