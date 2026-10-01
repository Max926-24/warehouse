package de.ait.warehouse.service;

import de.ait.warehouse.domain.Supplier;
import de.ait.warehouse.dto.mapping.SupplierMapper;
import de.ait.warehouse.dto.supplier.SupplierDto;
import de.ait.warehouse.dto.supplier.SupplierSaveDto;
import de.ait.warehouse.repository.SupplierRepository;
import de.ait.warehouse.service.interfaces.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository repository;
    private final SupplierMapper mapper;

    public SupplierServiceImpl(SupplierRepository repository, SupplierMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public SupplierDto save(SupplierSaveDto saveDto) {
        Supplier entity = mapper.mapDtoToEntity(saveDto);
        repository.save(entity);
        return mapper.mapEntityToDto(entity);
    }

    @Override
    public List<SupplierDto> findAllSuppliers() {
        return repository.findAll()
                .stream()
                .map(mapper::mapEntityToDto)
                .toList();
    }
}
