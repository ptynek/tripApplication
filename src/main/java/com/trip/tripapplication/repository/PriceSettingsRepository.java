package com.trip.tripapplication.repository;

import com.trip.tripapplication.domain.PriceSettings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface PriceSettingsRepository  extends CrudRepository<PriceSettings, Long> {

    @Override
    Optional<PriceSettings> findById(Long id);

    @Override
    List<PriceSettings> findAll();

    @Override
    PriceSettings save(final PriceSettings priceSettings);


}
