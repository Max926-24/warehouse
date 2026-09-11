package de.ait.warehouse.repository;

import de.ait.warehouse.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {


}
