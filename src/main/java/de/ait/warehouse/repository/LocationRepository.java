package de.ait.warehouse.repository;

import de.ait.warehouse.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
