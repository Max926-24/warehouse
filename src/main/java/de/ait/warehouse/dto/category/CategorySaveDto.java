package de.ait.warehouse.dto.category;

public class CategorySaveDto {

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
