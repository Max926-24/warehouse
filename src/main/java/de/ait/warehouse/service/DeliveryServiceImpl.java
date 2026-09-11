package de.ait.warehouse.service;

import de.ait.warehouse.domain.Delivery;
import de.ait.warehouse.domain.Item;
import de.ait.warehouse.domain.Supplier;
import de.ait.warehouse.dto.delivery.DeliveryDto;
import de.ait.warehouse.dto.delivery.DeliverySaveDto;
import de.ait.warehouse.dto.mapping.DeliveryMapper;
import de.ait.warehouse.exceptions.types.EntityNotFoundException;
import de.ait.warehouse.exceptions.types.PriceMismatchException;
import de.ait.warehouse.repository.DeliveryRepository;
import de.ait.warehouse.repository.ItemRepository;
import de.ait.warehouse.repository.SupplierRepository;
import de.ait.warehouse.service.interfaces.DeliveryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository repository;
    private final ItemRepository itemRepository;
    private final SupplierRepository supplierRepository;
    private final DeliveryMapper mapper;

    public DeliveryServiceImpl(DeliveryRepository repository, ItemRepository itemRepository,
                               SupplierRepository supplierRepository, DeliveryMapper mapper) {
        this.repository = repository;
        this.itemRepository = itemRepository;
        this.supplierRepository = supplierRepository;
        this.mapper = mapper;


    }

    @Override
    public DeliveryDto save(DeliverySaveDto dto) {
        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new EntityNotFoundException(Item.class, dto.getItemId()));
        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                .orElseThrow(() -> new EntityNotFoundException(Supplier.class, dto.getSupplierId()));

        if (dto.getPricePerUnit().compareTo(item.getPrice()) != 0) {
            throw new PriceMismatchException(
                    String.format("Price mismatch : expected %.2f but got %.2f",
                            item.getPrice(), dto.getPricePerUnit()));

        }
        item.setQuantity(item.getQuantity() + dto.getQuantity());
        itemRepository.save(item);

        Delivery delivery = new Delivery();
        delivery.setItem(item);
        delivery.setSupplier(supplier);
        delivery.setPricePerUnit(dto.getPricePerUnit());
        delivery.setDeliveryDate(dto.getDeliveryDate());
        delivery.setQuantity(dto.getQuantity());
        repository.save(delivery);

        return mapper.mapEntityToDTo(delivery);


    }

    @Override
    public List<DeliveryDto> findAllDeliveries() {
        return repository.findAll()
                .stream()
                .map(mapper::mapEntityToDTo)
                .toList();
    }
}
