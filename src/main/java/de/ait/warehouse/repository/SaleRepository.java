package de.ait.warehouse.repository;

import de.ait.warehouse.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
