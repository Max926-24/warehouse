package de.ait.warehouse.service;

import de.ait.warehouse.domain.Category;
import de.ait.warehouse.domain.Item;
import de.ait.warehouse.dto.item.ItemDto;
import de.ait.warehouse.dto.item.ItemSaveDto;
import de.ait.warehouse.dto.item.ItemUpdateDto;
import de.ait.warehouse.dto.mapping.ItemMapper;
import de.ait.warehouse.exceptions.types.EntityNotFoundException;
import de.ait.warehouse.repository.CategoryRepository;
import de.ait.warehouse.repository.ItemRepository;
import de.ait.warehouse.service.interfaces.ItemService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;
    private final ItemMapper mapper;
    private final CategoryRepository categoryRepository;

    public ItemServiceImpl(ItemRepository repository, ItemMapper mapper, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ItemDto save(ItemSaveDto saveDto) {
        Item entity = mapper.mapDtoToEntity(saveDto);

        if (saveDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(saveDto.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException(Category.class, saveDto.getCategoryId()));
            entity.setCategory(category);
        }
        entity.setActive(true);
        repository.save(entity);
        return mapper.mapEntityToDto(entity);
    }

    @Override
    public List<ItemDto> getAllActiveItems() {
        // List<ItemDto> result = new ArrayList<>();
        // for (Item item : repository.findAllByActiveTrue()) {
        // ItemDto dto = mapper.mapEntityToDto(item);
        //  result.add(dto);--oder:

        return repository.findAllByActiveTrue()
                .stream()
                .map(mapper::mapEntityToDto)
                .toList();
    }

    @Override
    public ItemDto getActiveItemById(Long id) {
        Item entity = repository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException(Item.class, id));

        return mapper.mapEntityToDto(entity);
    }

    @Override
    @Transactional
    public void update(Long id, ItemUpdateDto updateDto) {
        repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Item.class, id))
                .setPrice(updateDto.getNewPrice());


    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException(Item.class, id))
                .setActive(false);


    }

    @Override
    @Transactional
    public void restoreById(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Item.class, id))
                .setActive(true);


    }

    @Override
    public List<ItemDto> findByCategoryId(Long categoryId) {
        return repository.findByCategoryId(categoryId)
                .stream()
                .map(mapper::mapEntityToDto)
                .toList();
    }

    @Override
    public BigDecimal getTotalWarehouseValue() {
        return repository.findAll()
                .stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}
