package com.example.TestProject.service;

import static org.mockito.Mockito.*;

import com.example.TestProject.exception.EntityNotFoundException;
import com.example.TestProject.model.Device;
import com.example.TestProject.repository.DeviceRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class) //Used to create mock database for testing
public class DeviceServiceTests {

    @InjectMocks //Injects mocks into service
    private DeviceService deviceService;

    @Mock //Mock the repository
    private DeviceRepository deviceRepository;

    @Test
    void testSaveDevice(){
        Device mockDevice = new Device();
        mockDevice.setId(1L);
        mockDevice.setName("test");
        mockDevice.setDeviceType("test");

        when(deviceRepository.save(mockDevice)).thenReturn(mockDevice);

        Device savedDevice = deviceService.saveDevice(mockDevice);

        assertNotNull(savedDevice.getId());
        assertNotNull(savedDevice.getName());
        assertNotNull(savedDevice.getDeviceType());
    }
/*
    @Test
    void testGetDevice_found(){
        // (Step 1) Arrange: Create a fake device
        Device mockDevice = new Device();
        mockDevice.setId(1L);
        mockDevice.setName("TestDevice");
        mockDevice.setDeviceType("test");

        // Whenever deviceRepository.findById(1L) is called, Instead of calling the actual database it returns Optional.of(mockDevice)
        when(deviceRepository.findByName("TestDevice")).thenReturn(Optional.of(mockDevice));

        //Act: Call the service method
        Device foundDevice = deviceService.getDevice(1L);

        //Assert: Check if the expected and actual values match
        assertNotNull(foundDevice);
        assertEquals("Test Device", foundDevice.getName());
        assertEquals("test", foundDevice.getDeviceType());
    }

    @Test
    void testGetDevice_notFound(){
        //Arrange
        when(deviceRepository.findById(1L)).thenReturn(Optional.empty());

        //Act
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> deviceService.getDevice(1L));

        //Verify
        assertNotNull(thrown);
        assertEquals("Device with id 1 not found", thrown.getMessage());
    }

    @Test
    void testDeleteDevice_success(){
        //Arrange
        Long deviceId = 1L;
        when(deviceRepository.existsById(deviceId)).thenReturn(true);

        //Act
        String result = deviceService.deleteDevice(deviceId);

        //Assert
        verify(deviceRepository, times(1)).deleteById(deviceId);
        assertEquals("Device Deleted Successfully", result);
    }

    @Test
    void testDeleteDevice_failure(){
        //Arrange
        Long deviceId = 1L;
        when(deviceRepository.existsById(deviceId)).thenReturn(false);

        //Act
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> deviceService.deleteDevice(deviceId));

        //Assert
        verify(deviceRepository, never()).deleteById(deviceId);
        assertEquals("Device with id " + deviceId + " not found", thrown.getMessage());
    }
    */
}