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
    private Inventory getInventory(@PathVariable Long id){
        return inventoryService.getInventory(id);
    }

    @GetMapping("/test")
    private String doTest(){
        return "API endpoint working";
    }
}
