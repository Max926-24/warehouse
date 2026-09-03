package de.ait.warehouse.dto.item;

import java.math.BigDecimal;

public class ItemUpdateDto {

    private BigDecimal newPrice;

    public ItemUpdateDto() {
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }

    @Override
    public String toString() {
        return String.format("Item Update DTO: price-%.2f", newPrice);
    }
}

