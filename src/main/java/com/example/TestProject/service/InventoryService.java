package com.example.TestProject.service;

import com.example.TestProject.model.Device;

import java.util.List;

public interface InventoryService {

    //Methods to manage Device
    Device saveDevice(Device device); // Creates a new Device node in the database
    Device getDevice(Long id); // Gets a device node from the database by its Id
    List<Device> getAllDevices();
    Device updateDevice(Device device); //Updates a device node in the database
    String deleteDevice(Long id);  //Deletes a device node  from the database
}
