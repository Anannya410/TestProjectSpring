package com.example.TestProject.service;

import com.example.TestProject.exception.EntityNotFoundException;
import com.example.TestProject.model.Device;
import com.example.TestProject.repository.DeviceRepository;
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
    private DeviceRepository deviceRepository;

    @Override
    public Device saveDevice(Device device) {
        log.info("Saving device " + device.getId());
        return deviceRepository.save(device);
    }

    @Override
    public Device getDevice(Long id) {
        log.info("Getting device " + id);
        return deviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Device with id " + id + " not found"));
    }

    @Override
    public Device updateDevice(Device device) {
        if(deviceRepository.existsById(device.getId())){
            log.info("Updating device " + device.getId());
            return deviceRepository.save(device);
        }
        log.info("Device not found for update with id " + device.getId());
        throw new EntityNotFoundException("Device not found for update with id " + device.getId());
    }

    @Override
    public String deleteDevice(Long id) {
        if(deviceRepository.existsById(id)) {
            log.info("Deleting device " + id);
            deviceRepository.deleteById(id);
            return "Device Deleted Successfully";
        }
        log.info("Device with id " + id + " not found");
        throw new EntityNotFoundException("Device with id " + id + " not found");
    }
}
