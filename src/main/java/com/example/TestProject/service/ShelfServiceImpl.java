package com.example.TestProject.service;

import com.example.TestProject.exception.EntityNotFoundException;
import com.example.TestProject.model.Device;
import com.example.TestProject.model.Shelf;
import com.example.TestProject.model.ShelfPosition;
import com.example.TestProject.repository.DeviceRepository;
import com.example.TestProject.repository.ShelfPositionRepository;
import com.example.TestProject.repository.ShelfRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelfServiceImpl implements ShelfService{
    private static final Logger log = LoggerFactory.getLogger(DeviceService.class);

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private ShelfRepository shelfRepository;

    @Autowired
    private ShelfPositionRepository shelfPositionRepository;

    @Override
    public Shelf saveShelf(Shelf shelf) {
        log.info("Saving shelf with id "+shelf.getId());
        return shelfRepository.save(shelf);
    }

    @Override
    public Shelf getShelfById(Long id) {
        log.info("Getting shelf with id "+ id);
        return shelfRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Shelf with id "+id+" not found"));
    }

    @Override
    public List<Shelf> getAllShelves() {
        log.info("Getting all shelves");
        return shelfRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }


    @Override
    public ShelfPosition saveShelfPosition(ShelfPosition shelfPosition) {
        log.info("Saving shelf position with id "+shelfPosition.getId());
        return shelfPositionRepository.save(shelfPosition);
    }

    @Override
    public ShelfPosition getShelfPositionById(Long id) {
        log.info("Getting shelf with id "+ id);
        return shelfPositionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Shelf with id "+id+" not found"));
    }

    @Override
    public List<ShelfPosition> getAllShelfPosition() {
       log.info("Getting all shelf positions");
       return shelfPositionRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public void addShelfPositionToDevice(Long deviceId, Long shelfPositionId) {
        //Find the nodes if they exist by their ID
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new EntityNotFoundException("Device with id "+deviceId+" not found"));

        ShelfPosition shelfPosition = shelfPositionRepository.findById(shelfPositionId)
                .orElseThrow(() -> new EntityNotFoundException("Shelf position with id "+shelfPositionId+" not found"));

        //Set relationship, update node values
        device.getShelfPosition().add(shelfPosition);
        shelfPosition.setDeviceId(deviceId);

        //Save the updated nodes
        deviceRepository.save(device);
        shelfPositionRepository.save(shelfPosition);
    }

    @Override
    public void addShelfToShelfPosition(Long shelfId, Long shelfPositionId) {
        Shelf shelf = shelfRepository.findById(shelfId)
                .orElseThrow(() -> new EntityNotFoundException("Shelf with id "+shelfId+" not found"));

        ShelfPosition shelfPosition = shelfPositionRepository.findById(shelfPositionId)
                .orElseThrow(() -> new EntityNotFoundException("Shelf position with id "+shelfPositionId+" not found"));

        // Check if the relationship already exists
        if (shelfPosition.getShelf() != null) {
            throw new IllegalStateException("ShelfPosition with id " + shelfPositionId + " is already assigned to another Shelf");
        }

        //Set relationship , update node values
        shelf.setShelfPositionId(shelfPositionId);
        shelfPosition.setShelf(shelf);

        //Save the updated nodes
        shelfRepository.save(shelf);
        shelfPositionRepository.save(shelfPosition);

    }
}
