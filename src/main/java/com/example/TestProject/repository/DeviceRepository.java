package com.example.TestProject.repository;

import com.example.TestProject.model.Device;
import com.example.TestProject.model.DeviceDTO;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends Neo4jRepository<Device, String> {

    @Query("MATCH (device: Device) WHERE device.name = $name"+ " OPTIONAL MATCH (device)-[r:HAS]->(sp:ShelfPosition)" + " RETURN device, collect(sp) AS shelfPositions")
    Optional<DeviceDTO> findByName(String name);

    @Query("MATCH (device: Device)" + "OPTIONAL MATCH (device)-[r:HAS]->(sp:ShelfPosition)" + "RETURN device, collect(sp) AS shelfPositions" + " ORDER BY device.id")
    List<DeviceDTO> findAllCustom();
}


//@Query("MATCH (device:Device) WHERE device.name = $name" + " OPTIONAL MATCH (device)-[r:HAS]->(shelfPosition:ShelfPosition) WITH collect(id(device)) AS nodes, collect(id(shelfPosition)) AS shelfPosition, collect(id(r)) AS r RETURN nodes, shelfPosition, r")