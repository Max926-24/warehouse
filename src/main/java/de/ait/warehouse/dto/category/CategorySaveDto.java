package de.ait.warehouse.dto.category;

import jakarta.validation.constraints.NotBlank;

public class CategorySaveDto {

    @NotBlank
    private String name;

    public CategorySaveDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("CategorySaveDto : name=%s", name);
    }
}
