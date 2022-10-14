package com.trip.tripapplication.repository;

import com.trip.tripapplication.domain.Cities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CitiesRepository extends CrudRepository<Cities, Long> {

    @Override
    Optional<Cities> findById(Long id);

    @Override
    Cities save(Cities cities);

    @Override
    List<Cities> findAll();
}
