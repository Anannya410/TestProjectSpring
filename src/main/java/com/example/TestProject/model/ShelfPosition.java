package com.example.TestProject.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
@Data
public class ShelfPosition {

    @Id @Getter
    private Long id;
    private String name;
    private Long deviceId;

    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private Shelf shelf;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }
}
