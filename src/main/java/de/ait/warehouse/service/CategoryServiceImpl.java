package de.ait.warehouse.service;

import de.ait.warehouse.domain.Category;
import de.ait.warehouse.dto.category.CategoryDto;
import de.ait.warehouse.dto.category.CategorySaveDto;
import de.ait.warehouse.dto.mapping.CategoryMapper;
import de.ait.warehouse.repository.CategoryRepository;
import de.ait.warehouse.service.interfaces.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CategoryDto save(CategorySaveDto saveDto) {
        Category entity = mapper.mapDtoToEntity(saveDto);
        repository.save(entity);
        return mapper.mapEntityToDto(entity);
    }

    @Override
    public List<CategoryDto> findAllCategories() {
        return repository.findAll()
                .stream()
                .map(mapper::mapEntityToDto)
                .toList();
    }
}
