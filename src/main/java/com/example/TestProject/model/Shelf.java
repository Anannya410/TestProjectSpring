package com.example.TestProject.model;


import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
public class Shelf {

    @Id
    private Long id;
    private String name;
    private String shelfType;
    private Long shelfPositionId;


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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShelfType(String shelfType) {
        this.shelfType = shelfType;
    }

    public void setShelfPositionId(Long shelfPositionId) {
        this.shelfPositionId = shelfPositionId;
    }
}
