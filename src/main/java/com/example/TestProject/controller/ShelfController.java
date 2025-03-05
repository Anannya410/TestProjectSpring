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

    //######### Managing Shelf ###########

    @PostMapping("/save")
    private Shelf saveShelf(@RequestBody Shelf shelf){
        return shelfService.saveShelf(shelf);
    }

    @GetMapping("get/{id}")
    private Shelf getShelfById(@PathVariable Long id){
        return shelfService.getShelfById(id);
    }

    @GetMapping("/getall")
    private List<Shelf> getAllShelves(){
        return shelfService.getAllShelves();
    }

    @PutMapping("/update")
    private Shelf updateShelf(@RequestBody Shelf shelf){
        return shelfService.updateShelf(shelf);
    }

    @DeleteMapping("/delete/{id}")
    private String deleteShelf(@PathVariable Long id){
        return shelfService.deleteShelf(id);
    }


    //######## Managing ShelfPosition ##########3

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

    @PutMapping("shelfposition/update")
    private ShelfPosition updateShelfPosition(@RequestBody ShelfPosition shelfPosition){
        return shelfService.updateShelfPosition(shelfPosition);
    }

    @DeleteMapping("shelfposition/delete/{id}")
    private String deleteShelfPosiotion(@PathVariable Long id){
        return shelfService.deleteShelfPosition(id);
    }

    // ########## Managing Relationships ##########

    @PostMapping("/relationship/device/shelfposition")
    private void addShelfPositionToDevice(@RequestBody DeviceShelfPositionRequest request){
        shelfService.addShelfPositionToDevice(request.getDeviceName(), request.getShelfPositionId());
    }

    @PostMapping("/relationship/shelf/shelfposition")
    private void addShelfPositionToShelf(@RequestBody ShelfToShelfPositionRequest request){
        shelfService.addShelfToShelfPosition(request.getShelfId(), request.getShelfPositionId());
    }

}
