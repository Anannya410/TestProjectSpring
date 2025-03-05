//This model is made to facilitate Post request for adding relationship between device and shelf
//Relationship at holds: (d:Device) -[HAS]-> (p:ShelfPosition) -[HAS]->(s:Shelf)

package com.example.TestProject.model;

public class DeviceShelfPositionRequest {
    private String deviceName;
    private Long shelfPositionId;

    public String getDeviceName() {
        return deviceName;
    }

    public Long getShelfPositionId() {
        return shelfPositionId;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setShelfPositionId(Long shelfPositionId) {
        this.shelfPositionId = shelfPositionId;
    }
}
