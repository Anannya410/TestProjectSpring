package com.example.TestProject.model;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
@Data
public class Shelf {

    @Id
    private Long id;
    private String name;
    private String shelfType;
    private Long shelfPositionId;

    @Relationship(type = "HAS", direction = Relationship.Direction.INCOMING)
    private ShelfPosition shelfPosition;

    public Long getId(){
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getShelfType() {
        return this.shelfType;
    }

    public Long getShelfPositionId() {
        return this.shelfPositionId;
    }
}
