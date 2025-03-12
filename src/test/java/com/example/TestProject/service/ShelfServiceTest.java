package com.example.TestProject.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.TestProject.exception.EntityNotFoundException;
import com.example.TestProject.model.Device;
import com.example.TestProject.model.Shelf;
import com.example.TestProject.model.ShelfDTO;
import com.example.TestProject.model.ShelfPosition;
import com.example.TestProject.model.ShelfPositionDTO;
import com.example.TestProject.repository.DeviceRepository;
import com.example.TestProject.repository.ShelfPositionRepository;
import com.example.TestProject.repository.ShelfRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ShelfServiceTest {

    @InjectMocks
    private ShelfServiceImpl shelfServiceImpl;

    @Mock
    private ShelfRepository shelfRepository;

    @Mock
    private ShelfPositionRepository shelfPositionRepository;

    @Mock
    private DeviceRepository deviceRepository;

    @Test
    void testSaveShelf(){
        //Arrange
        Shelf mockShelf = new Shelf();
        mockShelf.setId(1L);
        mockShelf.setName("TestShelf");
        mockShelf.setShelfType("Test");

        when(shelfRepository.save(mockShelf)).thenReturn(mockShelf);

        //Act
        Shelf savedShelf = shelfServiceImpl.saveShelf(mockShelf);

        //Assert
        assertNotNull(savedShelf);
        assertEquals(1L ,savedShelf.getId());
        assertEquals("TestShelf",savedShelf.getName());
        assertEquals("Test",savedShelf.getShelfType());
        assertNull(savedShelf.getShelfPosition());
    }

    @Test
    void testGetShelfById_found(){
        //Arrange
        Shelf mockShelf = new Shelf();
        mockShelf.setId(1L);
        mockShelf.setName("TestShelf");
        mockShelf.setShelfType("Test");

        ShelfDTO mockResult = new ShelfDTO(mockShelf, null, null);

        when(shelfRepository.findByIdCustom(1L)).thenReturn(Optional.of(mockResult));

        //Act
        Shelf foundShelf = shelfServiceImpl.getShelfById(1L);

        //Assert
        assertNotNull(foundShelf);
        assertEquals("TestShelf",foundShelf.getName());
    }

    @Test
    void testGetShelfById_notFound(){
        //Arrange
        when(shelfRepository.findByIdCustom(1L)).thenReturn(Optional.empty());

        //Act
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> shelfServiceImpl.getShelfById(1L));

        //Assert
        assertNotNull(thrown);
        assertEquals("Shelf with id 1 not found", thrown.getMessage());
    }

    @Test
    void testGetAllShelves(){
        //Arrange
        Shelf mockShelf1= new Shelf();
        mockShelf1.setId(1L);
        mockShelf1.setName("TestShelf1");

        Shelf mockShelf2 = new Shelf();
        mockShelf2.setId(2L);
        mockShelf2.setName("TestShelf2");

        ShelfDTO mockDTO1 = new ShelfDTO(mockShelf1, null, null);
        ShelfDTO mockDTO2 = new ShelfDTO(mockShelf2, null, null);

        List<ShelfDTO> mockResult = Arrays.asList(mockDTO1, mockDTO2);

        when(shelfRepository.findAllCustom()).thenReturn(mockResult);

        //Act
        List<Shelf> foundShelves= shelfServiceImpl.getAllShelves();

        //Assert
        assertNotNull(foundShelves);
        assertEquals(2, foundShelves.size());
        assertEquals("TestShelf1",foundShelves.get(0).getName());
        assertEquals("TestShelf2",foundShelves.get(1).getName());
    }

    @Test
    void testSaveShelfPosition(){
        //Arange
        ShelfPosition mockShelfPosition = new ShelfPosition();
        mockShelfPosition.setId(1L);
        mockShelfPosition.setName("Test ShelfPosition");

        when(shelfPositionRepository.save(mockShelfPosition)).thenReturn(mockShelfPosition);

        //Act
        ShelfPosition savedShelfPostion = shelfServiceImpl.saveShelfPosition(mockShelfPosition);

        //Assert
        assertNotNull(savedShelfPostion);
        assertEquals(1L ,savedShelfPostion.getId());
        assertEquals("Test ShelfPosition",savedShelfPostion.getName());
        // assertNull(savedShelfPostion.getDeviceId());
        assertNull(savedShelfPostion.getShelf());
    }

    @Test
    void testGetShelfPositionById_found(){
        //Arrange
        ShelfPosition mockShelfPosition = new ShelfPosition();
        mockShelfPosition.setId(1L);
        mockShelfPosition.setName("Test ShelfPosition");

        ShelfPositionDTO mockResult = new ShelfPositionDTO(mockShelfPosition, null, null, null, null, null, null);

        when(shelfPositionRepository.findByIdCustom(1l)).thenReturn(Optional.of(mockResult));

        //Act
        ShelfPosition foundShelfPostion = shelfServiceImpl.getShelfPositionById(1l);

        //Assert
        assertNotNull(foundShelfPostion);
        assertEquals("Test ShelfPosition",foundShelfPostion.getName());
    }

    @Test
    void testGetShelfPositionById_notFound(){
        //Arrange
        when(shelfPositionRepository.findByIdCustom(1L)).thenReturn(Optional.empty());

        //Act
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> shelfServiceImpl.getShelfPositionById(1L));

        //Assert
        assertNotNull(thrown);
        assertEquals("ShelfPosition with id 1 not found", thrown.getMessage());
    }

    @Test
    void testGetAllShelfPositions(){
        ShelfPosition mockShelfPosition = new ShelfPosition();
        mockShelfPosition.setId(1L);
        mockShelfPosition.setName("Test ShelfPosition");

        ShelfPosition mockShelfPosition2 = new ShelfPosition();
        mockShelfPosition2.setId(2L);
        mockShelfPosition2.setName("Test ShelfPosition2");

        ShelfPositionDTO mockDTO1 = new ShelfPositionDTO(mockShelfPosition, null, null, null, null, null, null);

        ShelfPositionDTO mockDTO2 = new ShelfPositionDTO(mockShelfPosition2, null, null, null, null, null, null);

        List<ShelfPositionDTO> mockResult = Arrays.asList(mockDTO1, mockDTO2);

        when(shelfPositionRepository.findAllCustom()).thenReturn(mockResult);

        List<ShelfPosition> foundShelfPositions = shelfServiceImpl.getAllShelfPosition();

        assertNotNull(foundShelfPositions);
        assertEquals(2, foundShelfPositions.size());
        assertEquals("Test ShelfPosition",foundShelfPositions.get(0).getName());
        assertEquals("Test ShelfPosition2",foundShelfPositions.get(1).getName());
    }
 
    @Test
    void testAddShelfPositionToDevice(){
        //Arrange
        ShelfPosition mockShelfPosition = new ShelfPosition();
        mockShelfPosition.setId(1L);
        mockShelfPosition.setName("Test ShelfPosition");

        Device mockDevice = new Device();
        mockDevice.setId(1L);
        mockDevice.setName("Test Device");
        mockDevice.setShelfPosition(new ArrayList<>()); // Initialize list before adding

        when(deviceRepository.findById(mockDevice.getName())).thenReturn(Optional.of(mockDevice));
        when(shelfPositionRepository.findById(1L)).thenReturn(Optional.of(mockShelfPosition));

        when(deviceRepository.save(mockDevice)).thenReturn(mockDevice);
        when(shelfPositionRepository.save(mockShelfPosition)).thenReturn(mockShelfPosition);

        //Act
        shelfServiceImpl.addShelfPositionToDevice(mockDevice.getName(), 1L);

        //Assert
        verify(deviceRepository, times(1)).save(mockDevice);
        verify(shelfPositionRepository, times(1)).save(mockShelfPosition);
    }

    @Test
        void testAddShelfToShelfPosition(){
        //Arrange
        ShelfPosition mockShelfPosition = new ShelfPosition();
        mockShelfPosition.setId(1L);
        mockShelfPosition.setName("Test ShelfPosition");

        Shelf mockShelf = new Shelf();
        mockShelf.setId(1L);
        mockShelf.setName("Test Shelf");
        mockShelf.setShelfType("Test");

        when(shelfPositionRepository.findById(1L)).thenReturn(Optional.of(mockShelfPosition));
        when(shelfRepository.findById(1L)).thenReturn(Optional.of(mockShelf));

        //Act
        shelfServiceImpl.addShelfToShelfPosition(1L, 1L);

        //Assert
        verify(shelfPositionRepository, times(1)).save(mockShelfPosition);
        verify(shelfRepository, times(1)).save(mockShelf);

    }

}
