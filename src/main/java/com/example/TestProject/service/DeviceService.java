package com.example.TestProject.service;

import com.example.TestProject.model.Inventory;
import com.example.TestProject.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeviceService implements InventoryService{
    private static final Logger log = LoggerFactory.getLogger(DeviceService.class);

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Inventory saveDevice(Inventory inventory) {
        log.info("Saving device " /*+ inventory.getId()*/);
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory getInventory(Long id) {
        log.info("Getting inventory " + id);
        return inventoryRepository.findById(id).orElse(null);
    }
}
