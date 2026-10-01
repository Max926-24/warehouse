package de.ait.warehouse.dto.mapping;


import de.ait.warehouse.domain.Supplier;
import de.ait.warehouse.dto.supplier.SupplierDto;
import de.ait.warehouse.dto.supplier.SupplierSaveDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    SupplierDto mapEntityToDto(Supplier entity);

    Supplier mapDtoToEntity(SupplierSaveDto dto);

}
