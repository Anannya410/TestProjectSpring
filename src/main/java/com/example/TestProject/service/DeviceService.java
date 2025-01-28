package com.example.TestProject.service;

import com.example.TestProject.exception.DeviceNotFoundException;
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
    public Inventory getDevice(Long id) {
        log.info("Getting inventory " + id);
        return inventoryRepository.findById(id).orElse(null);
    }

    @Override
    public Inventory updateDevice(Inventory inventory) {
        if(inventoryRepository.existsById(inventory.getId())){
            log.info("Updating inventory " + inventory.getId());
            return inventoryRepository.save(inventory);
        }
        log.info("Device not found for update with id " + inventory.getId());
        throw new DeviceNotFoundException("Device not found for update with id " + inventory.getId());
    }

    @Override
    public String deleteDevice(Long id) {
        if(inventoryRepository.existsById(id)) {
            log.info("Deleting device " + id);
            inventoryRepository.deleteById(id);
            return "Device Deleted Successfully";
        }
        log.info("Device with id " + id + " not found");
        throw new DeviceNotFoundException("Device with id " + id + " not found");
    }
}
