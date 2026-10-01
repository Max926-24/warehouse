package de.ait.warehouse.dto.location;

public class LocationDto {

    private Long id;
    private String block;
    private String shelf;

    public LocationDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return String.format("LocationDto : id = %d,block = %s,shelf = %s", id, block, shelf);
    }
}
