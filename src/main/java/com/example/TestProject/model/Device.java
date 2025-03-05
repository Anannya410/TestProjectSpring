package com.example.TestProject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;;

@Node
public class Device {

    private String deviceType;
    private Long id;
    @Id
    private String name;
    

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
