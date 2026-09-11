package de.ait.warehouse.dto.mapping;


import de.ait.warehouse.domain.Category;
import de.ait.warehouse.dto.category.CategoryDto;
import de.ait.warehouse.dto.category.CategorySaveDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto mapEntityToDto(Category entity);

    Category mapDtoToEntity(CategorySaveDto dto);

}
