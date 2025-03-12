package com.example.TestProject.service;

import com.example.TestProject.exception.EntityNotFoundException;
import com.example.TestProject.model.Device;
import com.example.TestProject.model.DeviceDTO;
import com.example.TestProject.model.ShelfPosition;
import com.example.TestProject.repository.DeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class DeviceService implements InventoryService{
    private static final Logger log = LoggerFactory.getLogger(DeviceService.class);

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private ShelfService shelfService;

    @Override
    public Device saveDevice(Device device) {
        if(device.getName()== null || device.getName() == ""){
            throw new EntityNotFoundException("Device name cannot be null or empty");
        }
        if(deviceRepository.existsById(device.getName())){
            throw new IllegalStateException("Device with name "+device.getName()+" already exists and name is has unique key constraint on it");
        }
        log.info("Saving device " + device.getName());
        return deviceRepository.save(device);
    }

    @Override
    public Device getDevice(String name) {
        Optional<DeviceDTO> result = deviceRepository.findByName(name);
        if(result.isPresent()){
            Device device = result.get().getDevice();
            List<ShelfPosition> shelfPositions = result.get().getShelfPositions();

            device.setShelfPosition(shelfPositions);
            return device;
        }

        throw new EntityNotFoundException("Device with name " + name + " not found");
    }

    @Override
    public List<Device> getAllDevices() {
        //return deviceRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        List<DeviceDTO> result = deviceRepository.findAllCustom();
        List<Device> deviceList = new ArrayList<>();

        if(result != null && !result.isEmpty()){
            for(DeviceDTO resultItem: result){
                Device device = resultItem.getDevice();
                List<ShelfPosition> shelfPositions = resultItem.getShelfPositions();

                device.setShelfPosition(shelfPositions);
                deviceList.add(device);
            }
        }
        return deviceList;
    }

    @Override
    public Device updateDevice(Device device) {
        if(deviceRepository.existsById(device.getName())){
            log.info("Updating device " + device.getName());
            return deviceRepository.save(device);
        }
        log.info("Device not found for update with id " + device.getName());
        throw new EntityNotFoundException("Device not found for update with id " + device.getName());
    }

    @Override
    public String deleteDevice(String name) {
        deviceRepository.findById(name).orElseThrow(() -> new EntityNotFoundException("Device with name " + name + " not found"));
        
        log.info("Deleting device " + name);
        deviceRepository.deleteById(name);

        // //Delete the entry of this device from all the associated ShelfPositions
        // for(ShelfPosition shelfPosition: device.getShelfPosition()){
        //     shelfPosition.setDeviceName(null);
        //     shelfService.updateShelfPosition(shelfPosition);
        // }
        
        return "Device Deleted Successfully";
    }
}