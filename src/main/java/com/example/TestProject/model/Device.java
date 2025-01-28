package com.example.TestProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
@Data
@Setter
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

    public void setShelfPosition(ShelfPosition shelfPosition) {
        this.shelfPosition = shelfPosition;
    }
}
