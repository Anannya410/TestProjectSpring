package com.example.TestProject.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.TestProject.model.Device;
import com.example.TestProject.service.DeviceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = DeviceController.class)
public class DeviceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private DeviceService deviceService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveDevice() throws Exception {
        Device mockDevice = new Device();
        mockDevice.setId(1L);
        mockDevice.setName("TestDevice");
        mockDevice.setDeviceType("test");

        when(deviceService.saveDevice(any(Device.class))).thenReturn(mockDevice);

        mockMvc.perform(post("/api/devices/save")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(mockDevice)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("TestDevice"))
                .andExpect(jsonPath("$.deviceType").value("test"));
    }

    @Test
    void testGetDevice() throws Exception {
        Device mockDevice = new Device();
        mockDevice.setId(1L);
        mockDevice.setName("TestDevice");
        mockDevice.setDeviceType("test");

        when(deviceService.getDevice("TestDevice")).thenReturn(mockDevice);

        mockMvc.perform(get("/api/devices/TestDevice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("TestDevice"))
                .andExpect(jsonPath("$.deviceType").value("test"));
    }

    @Test
    void testUpdateDevice() throws Exception {
        Device mockDevice = new Device();
        mockDevice.setId(1L);
        mockDevice.setName("TestDevice");
        mockDevice.setDeviceType("test");

        when(deviceService.updateDevice(any(Device.class))).thenReturn(mockDevice);

        mockMvc.perform(put("/api/devices/update")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(mockDevice)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("TestDevice"))
                .andExpect(jsonPath("$.deviceType").value("test"));
    }

    @Test
    void testDeleteDevice() throws Exception {
        String deviceName = "TestDevice";

        when(deviceService.deleteDevice(deviceName)).thenReturn("Device Deleted Successfully");

        mockMvc.perform(delete("/api/devices/delete/{name}", deviceName))
                .andExpect(status().isOk());
    }
}
