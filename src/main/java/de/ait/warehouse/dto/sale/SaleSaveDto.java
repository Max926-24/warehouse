package de.ait.warehouse.dto.sale;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class SaleSaveDto {

    @NotNull
    private Long itemId;

    @Min(1)
    @NotNull
    private Integer quantity;

    @NotNull
    private LocalDateTime saleDate;

    public SaleSaveDto() {
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
        return String.format("SaleSaveDto: itemId = %d, quantity = %d,saleDate = %s",itemId,quantity,saleDate);
    }
}
