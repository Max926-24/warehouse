package de.ait.warehouse.service;

import de.ait.warehouse.domain.Item;
import de.ait.warehouse.domain.Sale;
import de.ait.warehouse.dto.mapping.SaleMapper;
import de.ait.warehouse.dto.sale.SaleDto;
import de.ait.warehouse.dto.sale.SaleSaveDto;
import de.ait.warehouse.exceptions.types.EntityNotFoundException;
import de.ait.warehouse.exceptions.types.InsufficientStockException;
import de.ait.warehouse.repository.ItemRepository;
import de.ait.warehouse.repository.SaleRepository;
import de.ait.warehouse.service.interfaces.SaleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository repository;
    private final ItemRepository itemRepository;
    private final SaleMapper mapper;

    public SaleServiceImpl(SaleRepository repository, ItemRepository itemRepository, SaleMapper mapper) {
        this.repository = repository;
        this.itemRepository = itemRepository;
        this.mapper = mapper;
    }

    @Override
    public SaleDto save(SaleSaveDto dto) {
        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new EntityNotFoundException(Item.class, dto.getItemId()));

        if (dto.getQuantity() > item.getQuantity()) {
            throw new InsufficientStockException(
                    String.format("Insufficient stock : available %d but request %d", item.getQuantity(), dto.getQuantity())
            );
        }

        item.setQuantity(item.getQuantity() - dto.getQuantity());
        itemRepository.save(item);

        Sale sale = new Sale();
        sale.setItem(item);
        sale.setQuantity(dto.getQuantity());
        sale.setSaleDate(dto.getSaleDate());
        repository.save(sale);

        return mapper.mapEntityToDto(sale);


    }


    @Override
    public List<SaleDto> findAllSales() {
        return repository.findAll()
                .stream()
                .map(mapper::mapEntityToDto)
                .toList();
    }
}
