package com.example.TestProject.model;

public class ShelfDTO {
    private Shelf shelf;
    private Long shelfPositionId;
    private String shelfPositionName;

    public ShelfDTO (Shelf shelf, Long shelfPositionId, String shelfPositionName){
        this.shelf = shelf;
        this.shelfPositionId = shelfPositionId;
        this.shelfPositionName = shelfPositionName;
    }

    public Shelf getShelf(){
        return shelf;
    }

    public Long getShelfPositionId(){
        return shelfPositionId;
    }

    public String getShelfPositionName(){
        return shelfPositionName;
    }
}
