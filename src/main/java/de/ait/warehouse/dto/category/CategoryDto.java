package de.ait.warehouse.dto.category;

public class CategoryDto {

    private Long id;
    private String name;

    public CategoryDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("CategoryDto : id=%d, name=%s", id, name);
    }
}
