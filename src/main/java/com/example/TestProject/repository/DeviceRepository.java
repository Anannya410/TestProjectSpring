package com.example.TestProject.repository;

import com.example.TestProject.model.Device;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends Neo4jRepository<Device, Long> {
}
