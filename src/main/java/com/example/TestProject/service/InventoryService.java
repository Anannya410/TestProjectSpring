package com.example.TestProject.service;

import com.example.TestProject.model.Device;

public interface InventoryService {

    //Methods to manage Device
    Device saveDevice(Device device);
    Device getDevice(Long id);
    Device updateDevice(Device device);
    String deleteDevice(Long id);
}
