package com.ozan.exchange.api.repository;

import com.ozan.exchange.api.model.entity.Conversion;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversionRepository extends JpaRepository<Conversion, UUID> {
    List<Conversion> findByDate(LocalDate date);
}
