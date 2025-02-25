package com.example.TestProject.controller;

import com.example.TestProject.model.DeviceShelfPositionRequest;
import com.example.TestProject.model.Shelf;
import com.example.TestProject.model.ShelfPosition;
import com.example.TestProject.model.ShelfToShelfPositionRequest;
import com.example.TestProject.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/shelf")
@CrossOrigin(origins = "http://localhost:4200/")
public class ShelfController {

    @Autowired
    ShelfService shelfService;

    @PostMapping("/save")
    private Shelf saveShelf(@RequestBody Shelf shelf){
        return shelfService.saveShelf(shelf);
    }

    @GetMapping("get/{id}")
    private Shelf getShelfById(@PathVariable Long id){
        return shelfService.getShelfById(id);
    }

    @GetMapping("/get")
    private List<Shelf> getAllShelves(){
        return shelfService.getAllShelves();
    }

    @PostMapping("/shelfposition/save")
    private ShelfPosition saveShelfPosition(@RequestBody ShelfPosition shelfPosition){
        return shelfService.saveShelfPosition(shelfPosition);
    }

    @GetMapping("/shelfposition/get/{id}")
    private ShelfPosition getShelfPositionById(@PathVariable Long id){
        return shelfService.getShelfPositionById(id);
    }

    @GetMapping("shelfposition/get")
    private List<ShelfPosition> getAllShelfPosition(){
        return shelfService.getAllShelfPosition();
    }

    @PostMapping("/relationship/device/shelfposition")
    private void addShelfPositionToDevice(@RequestBody DeviceShelfPositionRequest request){
        shelfService.addShelfPositionToDevice(request.getDeviceId(), request.getShelfPositionId());
    }

    @PostMapping("/relationship/shelf/shelfposition")
    private void addShelfPositionToShelf(@RequestBody ShelfToShelfPositionRequest request){
        shelfService.addShelfToShelfPosition(request.getShelfPositionId(), request.getShelfPositionId());
    }

}
