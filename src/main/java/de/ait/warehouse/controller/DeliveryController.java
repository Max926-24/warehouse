package de.ait.warehouse.controller;


import de.ait.warehouse.dto.delivery.DeliveryDto;
import de.ait.warehouse.dto.delivery.DeliverySaveDto;
import de.ait.warehouse.service.interfaces.DeliveryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryService service;

    public DeliveryController(DeliveryService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryDto save(@Valid @RequestBody DeliverySaveDto saveDto) {
        return service.save(saveDto);
    }

    @GetMapping
    public List<DeliveryDto> getAll() {
        return service.findAllDeliveries();
    }


}
