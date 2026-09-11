package de.ait.warehouse.dto.delivery;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DeliverySaveDto {
    @NotNull
    Long itemId;

    @NotNull
    Long supplierId;

    @Min(1)
    @NotNull
    Integer quantity;

    @DecimalMin("0.0")
    @NotNull
    BigDecimal pricePerUnit;

    @NotNull
    LocalDateTime deliveryDate;

    public DeliverySaveDto() {
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return String.format("DeliverySaveDto : itemId = %d, supplierId = %d, quantity = %d , pricePerUnit = %.2f, deliveryDate = %s"
                , itemId, supplierId, quantity, pricePerUnit, deliveryDate);
    }
}
