package de.ait.warehouse.controller;


import de.ait.warehouse.dto.location.LocationDto;
import de.ait.warehouse.dto.location.LocationSaveDto;
import de.ait.warehouse.service.interfaces.LocationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocationDto save(@Valid @RequestBody LocationSaveDto saveDto) {
        return service.save(saveDto);
    }

    @GetMapping
    public List<LocationDto> getAll() {
        return service.findAllLocations();
    }
}
