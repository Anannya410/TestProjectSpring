package com.example.TestProject.service;

import com.example.TestProject.model.Inventory;

public interface InventoryService {
    Inventory saveDevice(Inventory inventory);
    Inventory getDevice(Long id);
    Inventory updateDevice(Inventory inventory);
    String deleteDevice(Long id);
}
