package de.ait.warehouse.dto.mapping;


import de.ait.warehouse.domain.Item;
import de.ait.warehouse.dto.item.ItemDto;
import de.ait.warehouse.dto.item.ItemSaveDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDto mapEntityToDto(Item entity);

    Item mapDtoToEntity(ItemSaveDto dto);


}
