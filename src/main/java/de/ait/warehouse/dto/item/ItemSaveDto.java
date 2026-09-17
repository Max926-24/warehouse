package de.ait.warehouse.dto.item;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ItemSaveDto {
    @NotBlank
    private String title;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

    @NotNull
    @Min(0)
    private Integer quantity;

    @NotNull
    private Long categoryId;

    public ItemSaveDto() {
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Item Save DTO: title-%s,price-%.2f,quantity-%d, categoryId-%d", title, price, quantity, categoryId);
    }
}

