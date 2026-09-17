package de.ait.warehouse.controller;

import de.ait.warehouse.dto.sale.SaleDto;
import de.ait.warehouse.dto.sale.SaleSaveDto;
import de.ait.warehouse.service.interfaces.SaleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService service;

    public SaleController(SaleService service) {
        this.service = service;

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaleDto save(@Valid @RequestBody SaleSaveDto saveDto) {
        return service.save(saveDto);
    }

    @GetMapping
    public List<SaleDto> getAll() {
        return service.findAllSales();
    }


}
