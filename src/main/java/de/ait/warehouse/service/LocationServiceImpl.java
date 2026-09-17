package de.ait.warehouse.service;

import de.ait.warehouse.domain.Location;
import de.ait.warehouse.dto.location.LocationDto;
import de.ait.warehouse.dto.location.LocationSaveDto;
import de.ait.warehouse.dto.mapping.LocationMapper;
import de.ait.warehouse.repository.LocationRepository;
import de.ait.warehouse.service.interfaces.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository repository;
    private final LocationMapper mapper;

    public LocationServiceImpl(LocationRepository repository, LocationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public LocationDto save(LocationSaveDto saveDto) {
        Location entity = mapper.mapDtoToEntity(saveDto);
        repository.save(entity);
        return mapper.mapEntityToDto(entity);
    }

    @Override
    public List<LocationDto> findAllLocations() {
        return repository.findAll()
                .stream()
                .map(mapper::mapEntityToDto)
                .toList();
    }
}
