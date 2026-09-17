package de.ait.warehouse.service.interfaces;

import de.ait.warehouse.domain.Location;
import de.ait.warehouse.dto.location.LocationDto;
import de.ait.warehouse.dto.location.LocationSaveDto;

import java.util.List;

public interface LocationService {

    LocationDto save(LocationSaveDto dto);
    List<LocationDto> findAllLocations();
}
