package com.example.TestProject.controller;

import com.example.TestProject.model.Inventory;
import com.example.TestProject.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private InventoryService inventoryService;


    @PostMapping("/save")
    private Inventory saveDevice(@RequestBody Inventory inventory) {
        return inventoryService.saveDevice(inventory);
    }

    @GetMapping("/{id}")
    private Inventory getDevice(@PathVariable Long id){
        return inventoryService.getDevice(id);
    }

    @PutMapping("/update")
    private Inventory updateDevice(@RequestBody Inventory inventory){
        return inventoryService.updateDevice(inventory);
    }

    @DeleteMapping("delete/{id}")
    private String deleteDevice(@PathVariable Long id){
        return inventoryService.deleteDevice(id);
    }
}
