package de.ait.warehouse.controller;

import de.ait.warehouse.dto.category.CategoryDto;
import de.ait.warehouse.dto.category.CategorySaveDto;
import de.ait.warehouse.service.interfaces.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto save(@RequestBody CategorySaveDto saveDto) {
        return service.save(saveDto);

    }

    @GetMapping
    public List<CategoryDto> getAll() {
        return service.findAllCategories();
    }
}
