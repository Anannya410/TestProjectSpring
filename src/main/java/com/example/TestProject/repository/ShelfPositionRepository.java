package com.example.TestProject.repository;

import com.example.TestProject.model.ShelfPosition;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ShelfPositionRepository extends Neo4jRepository<ShelfPosition, Long> {
}
