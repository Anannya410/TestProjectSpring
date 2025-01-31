package com.example.TestProject.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.TestProject.model.DeviceShelfPositionRequest;
import com.example.TestProject.model.Shelf;
import com.example.TestProject.model.ShelfPosition;
import com.example.TestProject.model.ShelfToShelfPositionRequest;
import com.example.TestProject.service.ShelfServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;


@WebMvcTest(controllers = ShelfController.class)
public class ShelfControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ShelfServiceImpl shelfServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveShelf() throws Exception {
        Shelf mockShelf = new Shelf();
        mockShelf.setId(1L);
        mockShelf.setName("Test Shelf");
        mockShelf.setShelfType("test");

        when(shelfServiceImpl.saveShelf(any(Shelf.class))).thenReturn(mockShelf);

        mockMvc.perform(post("/api/shelf/save")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(mockShelf)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Shelf"))
                .andExpect(jsonPath("$.shelfType").value("test"));
    }

    @Test
    void testGetShelfById() throws Exception {
        Shelf mockShelf = new Shelf();
        mockShelf.setId(1L);
        mockShelf.setName("Test Shelf");
        mockShelf.setShelfType("test");

        when(shelfServiceImpl.getShelfById(1L)).thenReturn(mockShelf);

        mockMvc.perform(get("/api/shelf/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Shelf"))
                .andExpect(jsonPath("$.shelfType").value("test"));
    }
    @Test
    void testGetAllShelves() throws Exception {
        Shelf shelf1 = new Shelf();
        shelf1.setId(1L);
        shelf1.setName("Test Shelf1");

        Shelf shelf2 = new Shelf();
        shelf2.setId(2L);
        shelf2.setName("Test Shelf2");

        List<Shelf> shelfList = Arrays.asList(shelf1, shelf2);
        when(shelfServiceImpl.getAllShelves()).thenReturn(shelfList);

        mockMvc.perform(get("/api/shelf/get"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[0].name").value("Test Shelf1"))
                .andExpect(jsonPath("$[1].name").value("Test Shelf2"));
    }

    @Test
    void testSaveShelfPosition() throws Exception {
        ShelfPosition mockShelfPosition = new ShelfPosition();
        mockShelfPosition.setId(1L);
        mockShelfPosition.setName("Test ShelfPosition");

        when(shelfServiceImpl.saveShelfPosition(any(ShelfPosition.class))).thenReturn(mockShelfPosition);

        mockMvc.perform(post("/api/shelf/shelfposition/save")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(mockShelfPosition)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test ShelfPosition"));
    }

    @Test
    void testGetShelfPositionById() throws Exception {
        ShelfPosition mockShelfPosition = new ShelfPosition();
        mockShelfPosition.setId(1L);
        mockShelfPosition.setName("Test ShelfPosition");

        when(shelfServiceImpl.getShelfPositionById(1L)).thenReturn(mockShelfPosition);

        mockMvc.perform(get("/api/shelf/shelfposition/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test ShelfPosition"));
    }

    @Test
    void testGetAllShelfPositions() throws Exception {
        ShelfPosition shelfPosition1 = new ShelfPosition();
        shelfPosition1.setId(1L);
        shelfPosition1.setName("Test ShelfPosition1");

        ShelfPosition shelfPosition2 = new ShelfPosition();
        shelfPosition2.setId(2L);
        shelfPosition2.setName("Test ShelfPosition2");

        List<ShelfPosition> shelfPositions = Arrays.asList(shelfPosition1, shelfPosition2);
        when(shelfServiceImpl.getAllShelfPosition()).thenReturn(shelfPositions);

        mockMvc.perform(get("/api/shelf/shelfposition/get"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[0].name").value("Test ShelfPosition1"))
                .andExpect(jsonPath("$[1].name").value("Test ShelfPosition2"));
    }

    @Test
    void testAddShelfPositionToDevice() throws Exception {
        DeviceShelfPositionRequest request = new DeviceShelfPositionRequest();
        request.setDeviceId(1L);
        request.setShelfPositionId(1L);

        doNothing().when(shelfServiceImpl).addShelfPositionToDevice(1L, 1L);

        mockMvc.perform(post("/api/shelf/relationship/device/shelfposition")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(shelfServiceImpl, times(1)).addShelfPositionToDevice(1L, 1L);
    }

    void testAddShelfToShelfPosition() throws Exception {
        ShelfToShelfPositionRequest request = new ShelfToShelfPositionRequest();
        request.setShelfId(1L);
        request.setShelfPositionId(1L);

        doNothing().when(shelfServiceImpl).addShelfToShelfPosition(1L, 1L);

        mockMvc.perform(post("/api/shelf/relationship/shelf/shelfposition")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(shelfServiceImpl, times(1)).addShelfToShelfPosition(1L, 1L);
    }
}
