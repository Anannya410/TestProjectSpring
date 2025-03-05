package com.example.TestProject.service;

import com.example.TestProject.model.Device;

import java.util.List;

public interface InventoryService {

    //Methods to manage Device
    Device saveDevice(Device device); // Creates a new Device node in the database
    Device getDevice(String name); // Gets a device node from the database by its Id
    List<Device> getAllDevices();
    Device updateDevice(Device device); //Updates a device node in the database
    String deleteDevice(String name);  //Deletes a device node  from the database
}
