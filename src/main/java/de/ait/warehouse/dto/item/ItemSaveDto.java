package de.ait.warehouse.dto.item;

import java.math.BigDecimal;

public class ItemSaveDto {

    private String title;
    private BigDecimal price;
    private Integer quantity;

    public ItemSaveDto() {
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
        return String.format("Item Save DTO: title-%s,price-%.2f,quantity-%d", title, price, quantity);
    }
}

