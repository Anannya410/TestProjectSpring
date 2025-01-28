package com.example.TestProject.controller;

import com.example.TestProject.model.Device;
import com.example.TestProject.model.Device;
import com.example.TestProject.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private InventoryService inventoryService;


    @PostMapping("/save")
    private Device saveDevice(@RequestBody Device device) {
        return inventoryService.saveDevice(device);
    }

    @GetMapping("/{id}")
    private Device getDevice(@PathVariable Long id){
        return inventoryService.getDevice(id);
    }

    @PutMapping("/update")
    private Device updateDevice(@RequestBody Device inventory){
        return inventoryService.updateDevice(inventory);
    }

    @DeleteMapping("delete/{id}")
    private String deleteDevice(@PathVariable Long id){
        return inventoryService.deleteDevice(id);
    }
}
