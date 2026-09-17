package de.ait.warehouse.service.interfaces;

import de.ait.warehouse.dto.sale.SaleDto;
import de.ait.warehouse.dto.sale.SaleSaveDto;

import java.util.List;

public interface SaleService {

    SaleDto save(SaleSaveDto dto);
    List<SaleDto> findAllSales();
}
