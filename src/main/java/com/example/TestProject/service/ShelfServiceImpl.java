package com.example.TestProject.service;

import com.example.TestProject.exception.EntityNotFoundException;
import com.example.TestProject.model.Device;
import com.example.TestProject.model.Shelf;
import com.example.TestProject.model.ShelfPosition;
import com.example.TestProject.model.ShelfPositionDTO;
import com.example.TestProject.repository.DeviceRepository;
import com.example.TestProject.repository.ShelfPositionRepository;
import com.example.TestProject.repository.ShelfRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

@Service
public class ShelfServiceImpl implements ShelfService{
    private static final Logger log = LoggerFactory.getLogger(DeviceService.class);

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private ShelfRepository shelfRepository;

    @Autowired
    private ShelfPositionRepository shelfPositionRepository;

    //####### Managing Shelf ##############
    @Override
    public Shelf saveShelf(Shelf shelf) {
        log.info("Saving shelf with id "+shelf.getId());
        if(shelfRepository.existsById(shelf.getId())){
            throw new IllegalStateException("ID " + shelf.getId() + " is already assigned to another Shelf");
        }
        else{
            return shelfRepository.save(shelf);
        }
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
    public Shelf updateShelf(Shelf shelf){
        if(shelfRepository.existsById(shelf.getId())){
            log.info("Updating Shelf with id "+shelf.getId());
            return shelfRepository.save(shelf);
        }
        
        throw new EntityNotFoundException("Shelf with Id "+shelf.getId()+" not found");
    }

    @Override
    public String deleteShelf(Long id){
        if(shelfRepository.existsById(id)){
            shelfRepository.deleteById(id);
            return "Shelf Deleted succefully";
        }
        else{
            throw new EntityNotFoundException("Shelf with id "+id+" not found");
        }
    }

    //########### Managing ShelfPosition ##############
    @Override
    public ShelfPosition saveShelfPosition(ShelfPosition shelfPosition) {
        log.info("Saving shelf position with id "+shelfPosition.getId());
        if(shelfPositionRepository.existsById(shelfPosition.getId())){
            throw new IllegalStateException("Id "+shelfPosition.getId()+" already assigned to another ShelfPosition");
        }
        else{
            return shelfPositionRepository.save(shelfPosition);
        }
    }

    @Override
    public ShelfPosition getShelfPositionById(Long id) {
        log.info("Getting shelf with id "+ id);
        Optional<ShelfPositionDTO> result = shelfPositionRepository.findByIdCustom(id);
        if(result.isPresent()){
            ShelfPosition shelfPosition = result.get().getShelfPosition();

            Device device = new Device();
            Shelf shelf = new Shelf();

            device.setId(result.get().getDeviceId());
            device.setName(result.get().getDeviceName());
            device.setDeviceType(result.get().getDeviceType());
            shelfPosition.setDevice(device);

            shelf.setId(result.get().getShelfId());
            shelf.setName(result.get().getShelfName());
            shelf.setShelfType(result.get().getShelfType());
            shelfPosition.setShelf(shelf);

            return shelfPosition;
        }

        throw new EntityNotFoundException("ShelfPosition with id " + id + " not found");

        // return shelfPositionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ShelfPosition with id " + id + " not found"));
    }

    @Override
    public List<ShelfPosition> getAllShelfPosition() {
       log.info("Getting all shelf positions");
      // return shelfPositionRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

      List<ShelfPositionDTO> result = shelfPositionRepository.findAllCustom();
      List<ShelfPosition> shelfPositionList = new ArrayList<>();
        
      log.info("RESULT OF DTO "+result);
      if(result != null){
        for(ShelfPositionDTO dto: result){
            ShelfPosition shelfPosition = dto.getShelfPosition();
            
            Device device = new Device();
            Shelf shelf = new Shelf();

            device.setId(dto.getDeviceId());
            device.setName(dto.getDeviceName());
            device.setDeviceType(dto.getDeviceType());
            shelfPosition.setDevice(device);

            shelf.setId(dto.getShelfId());
            shelf.setName(dto.getShelfName());
            shelf.setShelfType(dto.getShelfType());
            shelfPosition.setShelf(shelf);

            // // //Optional<Device> deviceResult = shelfPositionRepository.findAssociatedDevice(shelfPosition.getId());
            // // //if(deviceResult.isPresent()){
            // // String deviceName = shelfPositionRepository.findAssociatedDevice(shelfPosition.getId());
            // // if(deviceName != null){
            // //     Device device = new Device();
            // //     device.setName(deviceName);
            // //     shelfPosition.setDevice(device);
            // // }

            // TempDeviceDTO deviceDTO = shelfPositionRepository.findAssociatedDevice(shelfPosition.getId());
            // if(deviceDTO != null){
            //     Device device = new Device();
            //     device.setId(deviceDTO.getId());
            //     device.setName(deviceDTO.getName());
            //     device.setDeviceType(deviceDTO.getDeviceType());

            //     shelfPosition.setDevice(device);
            // }

            // log.info("FOR SHELF POSITION ID "+shelfPosition.getId());
            // Long shelfId = shelfPositionRepository.findAssociatedShelf(shelfPosition.getId());
            // if(shelfId != null){
            //     Shelf shelf = new Shelf();
            //     shelf.setId(shelfId);
            //     shelfPosition.setShelf(shelf);
            // }

            shelfPositionList.add(shelfPosition);
        }
      }
      return shelfPositionList;
    }

    @Override
    public ShelfPosition updateShelfPosition(ShelfPosition shelfPosition){
        if(shelfPositionRepository.existsById(shelfPosition.getId())){
            return shelfPositionRepository.save(shelfPosition);
        }
        throw new EntityNotFoundException("ShelfPosition with id "+shelfPosition.getId()+" not found");
    }

    @Override
    public String deleteShelfPosition(Long id){

        ShelfPosition shelfPosition = shelfPositionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ShelfPosition with id "+id+" not found"));

        shelfPositionRepository.deleteById(id);
        
        //Delete entry from associated shelf
        Shelf shelf = shelfPosition.getShelf();
        shelf.setShelfPositionId(null);
        updateShelf(shelf);
        
        return "ShelfPosition Deleted successfully";
    }


    //####### Managing Relationships #########
    @Override
    public void addShelfPositionToDevice(String deviceName, Long shelfPositionId) {
        //Find the nodes if they exist by their ID
        Device device = deviceRepository.findById(deviceName)
                .orElseThrow(() -> new EntityNotFoundException("Device with id "+deviceName+" not found"));

        ShelfPosition shelfPosition = shelfPositionRepository.findById(shelfPositionId)
                .orElseThrow(() -> new EntityNotFoundException("Shelf position with id "+shelfPositionId+" not found"));

        if (shelfPosition.getDevice() != null){
            throw new IllegalStateException("ShelfPosition with id " +shelfPositionId+ " is already assigned to Device with name "+shelfPosition.getDevice().getName());
        }

        //Set relationship, update node values
        device.getShelfPosition().add(shelfPosition);
        shelfPosition.setDevice(device);

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

        if(shelf.getShelfPositionId() != null) {
            throw new IllegalStateException("Shelf with id " + shelfId + " is already assigned to another shelfPostion");
        }

        //Set relationship , update node values
        shelf.setShelfPositionId(shelfPositionId);
        shelfPosition.setShelf(shelf);

        //Save the updated nodes
        shelfRepository.save(shelf);
        shelfPositionRepository.save(shelfPosition);

    }
}
