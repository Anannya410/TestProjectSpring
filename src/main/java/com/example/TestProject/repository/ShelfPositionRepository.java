package com.example.TestProject.repository;

import com.example.TestProject.model.ShelfPosition;
import com.example.TestProject.model.ShelfPositionDTO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface ShelfPositionRepository extends Neo4jRepository<ShelfPosition, Long> {
    @Query("MATCH (sp:ShelfPosition) " + 
    "WHERE sp.id = $id " +
    "OPTIONAL MATCH (sp)<-[:HAS]-(d:Device)" + 
    "OPTIONAL MATCH (sp)-[:HAS]->(s:Shelf) "+ 
    "RETURN sp AS shelfPosition, d.id AS deviceId, d.name AS deviceName, d.deviceType AS deviceType, s.id AS shelfId, s.name AS shelfName, s.shelfType AS shelfType")
    Optional<ShelfPositionDTO> findByIdCustom(Long id);    

    @Query("MATCH (sp:ShelfPosition) " + 
    "OPTIONAL MATCH (sp)<-[:HAS]-(d:Device)" + 
    "OPTIONAL MATCH (sp)-[:HAS]->(s:Shelf) "+ 
    "RETURN sp AS shelfPosition, d.id AS deviceId, d.name AS deviceName, d.deviceType AS deviceType, s.id AS shelfId, s.name AS shelfName, s.shelfType AS shelfType "
    + "ORDER BY sp.id")
    List<ShelfPositionDTO> findAllCustom();  
} 




// @Query("MATCH (shelfPosition: ShelfPosition) WHERE shelfPosition.id = $id" + " OPTIONAL MATCH (shelfPosition)<-[:HAS]-(device: Device)" + " OPTIONAL MATCH (shelfPosition)-[:HAS]->(shelf: Shelf)" + " RETURN shelfPosition, device, shelf")
    // Optional<ShelfPositionDTO> findByIdCustom(Long id);

    // @Query("MATCH (sp:ShelfPosition) " + "WITH sp, sp.id AS spId " +"OPTIONAL MATCH (sp: ShelfPosition {id: spId})-[:HAS*1]->(d:Device)" + "OPTIONAL MATCH (sp: ShelfPosition {id: spId})-[:HAS*1]->(s:Shelf) "+ "RETURN sp AS shelfPosition, d AS device, s AS shelf")
    // List<ShelfPositionDTO> findAllCustom();


// @Query("MATCH (sp:ShelfPosition)<-[:HAS]-(d:Device) " + "WHERE sp.id = $id " + "RETURN d.name")
    // String findAssociatedDevice(Long id);

    // @Query("MATCH (sp:ShelfPosition)<-[:HAS]-(d:Device) " + "WHERE sp.id = $id " + "RETURN d.id AS id, d.name AS name, d.deviceType AS deviceType")
    // TempDeviceDTO findAssociatedDevice(Long id);

    // @Query("MATCH (sp: ShelfPosition)-[:HAS]->(s:Shelf) " + "WHERE sp.id = $id " + "RETURN s.id")
    // Long findAssociatedShelf(Long id);