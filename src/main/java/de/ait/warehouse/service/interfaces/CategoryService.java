package de.ait.warehouse.service.interfaces;

import de.ait.warehouse.dto.category.CategoryDto;
import de.ait.warehouse.dto.category.CategorySaveDto;

import java.util.List;

public interface CategoryService {

    CategoryDto save(CategorySaveDto saveDto);
    List<CategoryDto> findAllCategories();


}
