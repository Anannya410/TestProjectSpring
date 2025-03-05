package com.example.TestProject.model;

public class ShelfPositionDTO {
    private ShelfPosition shelfPosition;
    Long deviceId;
    String deviceName;
    String deviceType;
    Long shelfId;
    String shelfName;
    String shelfType;

    public ShelfPositionDTO(ShelfPosition shelfPosition, Long deviceId, String deviceName, String deviceType, Long shelfId, String shelfName, String shelfType){
        this.shelfPosition = shelfPosition;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.shelfId = shelfId;
        this.shelfName = shelfName;
        this.shelfType = shelfType;
    }

    public ShelfPosition getShelfPosition(){
        return shelfPosition;
    }

    public Long getDeviceId(){
        return deviceId;
    }

    public String getDeviceName(){
        return deviceName;
    }

    public String getDeviceType(){
        return deviceType;
    }

    public Long getShelfId(){
        return shelfId;
    }

    public String getShelfName(){
        return shelfName;
    }

    public String getShelfType(){
        return shelfType;
    }
}
