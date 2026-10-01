package de.ait.warehouse.dto.mapping;


import de.ait.warehouse.domain.Location;
import de.ait.warehouse.dto.location.LocationDto;
import de.ait.warehouse.dto.location.LocationSaveDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationDto mapEntityToDto(Location entity);

    Location mapDtoToEntity(LocationSaveDto dto);

}
