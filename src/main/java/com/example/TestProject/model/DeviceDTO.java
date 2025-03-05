package com.example.TestProject.model;

import java.util.List;


public class DeviceDTO {
    private Device device;
    private List<ShelfPosition> shelfPositions;

    public DeviceDTO(Device device, List<ShelfPosition> shelfPositions){
        this.device = device;
        this.shelfPositions = shelfPositions;
    }

    public Device getDevice(){
        return device;
    }

    public List<ShelfPosition> getShelfPositions(){
        return shelfPositions;
    }
}
