//This model is made to facilitate Post request for adding relationship between device and shelf
//Relationship at holds: (d:Device) -[HAS]-> (p:ShelfPosition) -[HAS]->(s:Shelf)

package com.example.TestProject.model;

public class DeviceShelfPositionRequest {
    private Long deviceId;
    private Long shelfPositionId;

    public Long getDeviceId() {
        return deviceId;
    }

    public Long getShelfPositionId() {
        return shelfPositionId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public void setShelfPositionId(Long shelfPositionId) {
        this.shelfPositionId = shelfPositionId;
    }
}
