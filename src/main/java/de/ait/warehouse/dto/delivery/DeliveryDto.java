package de.ait.warehouse.dto.delivery;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DeliveryDto {

    private Long id;
    private Long itemId;
    private String itemTitle;
    private Long supplierId;
    private String supplierName;
    private Integer quantity;
    private BigDecimal pricePerUnit;
    private LocalDateTime deliveryDate;

    public DeliveryDto() {
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

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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
        return String.format("DeliveryDto : id = %d, itemId = %d,itemTitle = %s," + "supplierId = %d" +
                        ",supplierName = %s," + "quantity = %d,pricePerUnit = %.2f,deliveryDate = %s ",
                id, itemId, itemTitle, supplierId,
                supplierName, quantity, pricePerUnit, deliveryDate);

    }
}
