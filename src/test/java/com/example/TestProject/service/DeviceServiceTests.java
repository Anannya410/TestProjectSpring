package com.example.TestProject.service;

import static org.mockito.Mockito.*;

import com.example.TestProject.exception.EntityNotFoundException;
import com.example.TestProject.model.Device;
import com.example.TestProject.model.DeviceDTO;
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

    @Test
    void testGetDevice_found(){
        // (Step 1) Arrange: Create a fake device
        Device mockDevice = new Device();
        mockDevice.setId(1L);
        mockDevice.setName("TestDevice");
        mockDevice.setDeviceType("test");

        DeviceDTO deviceDTO = new DeviceDTO(mockDevice, null);

        // Whenever deviceRepository.findById(1L) is called, Instead of calling the actual database it returns Optional.of(mockDevice)
        when(deviceRepository.findByName("TestDevice")).thenReturn(Optional.of(deviceDTO));

        //Act: Call the service method
        Device foundDevice = deviceService.getDevice("TestDevice");

        //Assert: Check if the expected and actual values match
        assertNotNull(foundDevice);
        assertEquals(1, foundDevice.getId());
        assertEquals("TestDevice", foundDevice.getName());
        assertEquals("test", foundDevice.getDeviceType());
    }

    @Test
    void testGetDevice_notFound(){
        //Arrange
        when(deviceRepository.findByName("TestDevice")).thenReturn(Optional.empty());

        //Act
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> deviceService.getDevice("TestDevice"));

        //Verify
        assertNotNull(thrown);
        assertEquals("Device with name TestDevice not found", thrown.getMessage());
    }

    @Test
    void testDeleteDevice_success(){
        //Arrange
        Device mockDevice = new Device();
        mockDevice.setId(1L);
        mockDevice.setName("TestDevice");
        mockDevice.setDeviceType("test");

        when(deviceRepository.findById(mockDevice.getName())).thenReturn(Optional.of(mockDevice));

        //Act
        String result = deviceService.deleteDevice(mockDevice.getName());

        //Assert
        verify(deviceRepository, times(1)).deleteById(mockDevice.getName());
        assertEquals("Device Deleted Successfully", result);
    }

    @Test
    void testDeleteDevice_failure(){
        //Arrange
        String deviceName = "TestDevice";
        when(deviceRepository.findById(deviceName)).thenReturn(Optional.empty());

        //Act
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> deviceService.deleteDevice(deviceName));

        //Assert
        verify(deviceRepository, never()).deleteById(deviceName);
        assertEquals("Device with name " + deviceName+ " not found", thrown.getMessage());
    }
    
}