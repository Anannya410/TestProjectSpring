package com.example.TestProject.model;

public class ShelfDTO {
    private Shelf shelf;
    private Long shelfPositionId;
    private String shelfPositionName;
    private Long deviceId;
    private String deviceName;
    private String deviceType;

    public ShelfDTO (Shelf shelf, Long shelfPositionId, String shelfPositionName, Long deviceId, String deviceName, String deviceType){
        this.shelf = shelf;
        this.shelfPositionId = shelfPositionId;
        this.shelfPositionName = shelfPositionName;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
    }

    public Shelf getShelf(){
        return shelf;
    }

    public Long getShelfPositionId(){
        return shelfPositionId;
    }

    public String getShelfPositionName(){
        return shelfPositionName;
    }

    public Long getDeviceID() {
        return deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getDeviceType() {
        return deviceType;
    }
}
