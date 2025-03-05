package com.example.TestProject.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Node
public class ShelfPosition {

    @Id
    private Long id;
    private String name;

    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private Device device;

    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private Shelf shelf;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Device getDevice() {
        return this.device;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
