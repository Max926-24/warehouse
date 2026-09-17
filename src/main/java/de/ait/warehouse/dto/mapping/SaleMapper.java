package de.ait.warehouse.dto.mapping;


import de.ait.warehouse.domain.Sale;
import de.ait.warehouse.dto.sale.SaleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleMapper {

    @Mapping(target = "itemId", source = "item.id")
    SaleDto mapEntityToDto(Sale entity);


}
