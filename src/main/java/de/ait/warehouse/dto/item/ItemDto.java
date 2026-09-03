package de.ait.warehouse.dto.item;

import java.math.BigDecimal;

public class ItemDto {

    private Long id;
    private String title;
    private BigDecimal price;
    private Integer quantity;


    public ItemDto() {
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return String.format("Item DTO: id-%d,title-%s,price-%.2f ,quantity-%d", id, title, price, quantity);
    }
}
