package de.ait.warehouse.controller;

import de.ait.warehouse.dto.item.ItemDto;
import de.ait.warehouse.dto.item.ItemSaveDto;
import de.ait.warehouse.dto.item.ItemUpdateDto;
import de.ait.warehouse.service.interfaces.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto save(@Valid @RequestBody ItemSaveDto saveDto) {
        return service.save(saveDto);

    }

    @GetMapping
    public List<ItemDto> getAll() {
        return service.getAllActiveItems();
    }

    @GetMapping("/{id}")
    public ItemDto getById(@PathVariable Long id) {
        return service.getActiveItemById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody ItemUpdateDto updateDto) {
        service.update(id, updateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/{id}/restore")
    public void restoreById(@PathVariable Long id) {
        service.restoreById(id);
    }

    @GetMapping("/by-category/{categoryId}")
    public List<ItemDto> getByCategory(@PathVariable Long categoryId) {
        return service.findByCategoryId(categoryId);
    }

    @GetMapping("/total-value")
    public ResponseEntity<BigDecimal> getTotalWarehouseValue() {
        return ResponseEntity.ok(service.getTotalWarehouseValue());
    }


}
