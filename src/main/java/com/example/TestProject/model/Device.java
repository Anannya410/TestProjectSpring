package com.example.TestProject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
public class Device {

    @Id
    private Long id;
    private String name;
    private String deviceType;

    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private ShelfPosition shelfPosition;

    public Long getId(){
       return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getDeviceType(){
        return this.deviceType;
    }

    public ShelfPosition getShelfPosition() {
        return shelfPosition;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public void setShelfPosition(ShelfPosition shelfPosition) {
        this.shelfPosition = shelfPosition;
    }
}
