package de.ait.warehouse.repository;

import de.ait.warehouse.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository <Item,Long>{

    List<Item> findAllByActiveTrue();
    Optional<Item> findByIdAndActiveTrue(Long id);
    long countByActiveTrue();


}
