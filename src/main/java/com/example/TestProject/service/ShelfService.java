package com.example.TestProject.service;

import com.example.TestProject.model.Shelf;
import com.example.TestProject.model.ShelfPosition;

import java.util.List;

public interface ShelfService {
    //Managing Shelves and Shelf Positions
    Shelf saveShelf(Shelf shelf); //Create a shelf node in the database
    Shelf getShelfById(Long id); //Gets a shelf node from the database by its Id
    List<Shelf> getAllShelves(); //Gets all shef nodes present in the database
    ShelfPosition saveShelfPosition(ShelfPosition shelfPosition); //Creates a shelf position in the database
    ShelfPosition getShelfPositionById(Long id); //Gets a self position from the database by its Id
    List<ShelfPosition> getAllShelfPosition(); //Gets all shelf position from the database

    //Adding Relationships
    void addShelfPositionToDevice(Long deviceId, Long shelfPositionId); //Add an outgoing relation from device node to shelf position node
    void addShelfToShelfPosition(Long shelfId, Long shelfPositionId); //Adds an outgoing relation from shelf position node to shelf node
}
