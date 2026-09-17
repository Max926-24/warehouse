package de.ait.warehouse.dto.sale;

import java.time.LocalDateTime;

public class SaleDto {

    private Long id;
    private Long itemId;
    private Integer quantity;
    private LocalDateTime saleDate;

    public SaleDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    @Override
    public String toString() {
        return String.format("SaleDto: id = %d,itemId = %d, quantity = %d,saleDate = %s",id,itemId,quantity,saleDate);
    }
}
