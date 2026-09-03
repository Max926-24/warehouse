package de.ait.warehouse.service.interfaces;

import de.ait.warehouse.dto.item.ItemDto;
import de.ait.warehouse.dto.item.ItemSaveDto;

import de.ait.warehouse.dto.item.ItemUpdateDto;

import java.util.List;

public interface ItemService {

    ItemDto save(ItemSaveDto saveDto);


    List<ItemDto> getAllActiveItems();


    ItemDto getActiveItemById(Long id);


    void update(Long id, ItemUpdateDto updateDto);


    void deleteById(Long id);


    void restoreById(Long id);


}
