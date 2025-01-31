package com.example.TestProject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;;

@Node
public class Device {

    @Id
    private Long id;
    private String name;
    private String deviceType;

    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private List<ShelfPosition> shelfPosition;

    public Long getId(){
       return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getDeviceType(){
        return this.deviceType;
    }

    public List<ShelfPosition> getShelfPosition() {
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

    public void setShelfPosition(List<ShelfPosition> shelfPosition) {
        this.shelfPosition = shelfPosition;
    }
}
