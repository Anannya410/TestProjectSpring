///This model is made to facilitate Post request for adding relationship between shelf and shelfposition
//Relationship at holds: (d:Device) -[HAS]-> (p:ShelfPosition) -[HAS]->(s:Shelf)

package com.example.TestProject.model;

public class ShelfToShelfPositionRequest {
    private Long shelfId;
    private Long shelfPositionId;

    public Long getShelfId() {
        return this.shelfId;
    }

    public Long getShelfPositionId() {
        return this.shelfPositionId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public void setShelfPositionId(Long shelfPositionId) {
        this.shelfPositionId = shelfPositionId;
    }
}
