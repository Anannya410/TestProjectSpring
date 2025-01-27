package com.example.TestProject.service;

import com.example.TestProject.model.Inventory;

public interface InventoryService {
    Inventory saveDevice(Inventory inventory);
    Inventory getInventory(Long id);
}
