package com.example.TestProject.service;

import com.example.TestProject.model.Shelf;
import com.example.TestProject.model.ShelfPosition;

import java.util.List;

public interface ShelfService {
    Shelf saveShelf(Shelf shelf);
    Shelf getShelfById(Long id);
    List<Shelf> getAllShelves();
    ShelfPosition saveShelfPosition(ShelfPosition shelfPosition);
    ShelfPosition getShelfPositionById(Long id);
    List<ShelfPosition> getAllShelfPosition();

    //Adding Relationships
    void addShelfPositionToDevice(Long deviceId, Long shelfPositionId);
}
