package de.ait.warehouse.service.interfaces;

import de.ait.warehouse.dto.supplier.SupplierDto;
import de.ait.warehouse.dto.supplier.SupplierSaveDto;

import java.util.List;

public interface SupplierService {

    SupplierDto save(SupplierSaveDto dto);

    List<SupplierDto> findAllSuppliers();
}
