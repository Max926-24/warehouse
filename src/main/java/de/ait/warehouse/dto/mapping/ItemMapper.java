package de.ait.warehouse.dto.mapping;


import de.ait.warehouse.domain.Item;
import de.ait.warehouse.dto.item.ItemDto;
import de.ait.warehouse.dto.item.ItemSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    ItemDto mapEntityToDto(Item entity);

    @Mapping(target = "category", ignore = true)
    Item mapDtoToEntity(ItemSaveDto dto);


}
