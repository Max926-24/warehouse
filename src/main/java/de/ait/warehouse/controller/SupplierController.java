package de.ait.warehouse.controller;


import de.ait.warehouse.dto.supplier.SupplierDto;
import de.ait.warehouse.dto.supplier.SupplierSaveDto;
import de.ait.warehouse.service.interfaces.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService service;

    public SupplierController(SupplierService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SupplierDto save(@RequestBody SupplierSaveDto saveDto) {
        return service.save(saveDto);

    }

    @GetMapping
    public List<SupplierDto> getAll() {
        return service.findAllSuppliers();
    }
}
