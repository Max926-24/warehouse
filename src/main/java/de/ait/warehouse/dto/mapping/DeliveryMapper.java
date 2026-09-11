package de.ait.warehouse.dto.mapping;

import de.ait.warehouse.domain.Delivery;
import de.ait.warehouse.dto.delivery.DeliveryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {

    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "itemTitle", source = "item.title")
    @Mapping(target = "supplierId", source = "supplier.id")
    @Mapping(target = "supplierName", source = "supplier.name")
    DeliveryDto mapEntityToDTo(Delivery entity);

}
