package de.ait.warehouse.dto.location;

import jakarta.validation.constraints.NotBlank;

public class LocationSaveDto {

    @NotBlank
    private String block;

    @NotBlank
    private String shelf;

    public LocationSaveDto() {
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    @Override
    public String toString() {
        return String.format("LocationSaveDto: block = %s, shelf = %s", block, shelf);
    }
}
