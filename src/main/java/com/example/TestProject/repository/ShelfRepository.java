package com.example.TestProject.repository;

import com.example.TestProject.model.Shelf;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ShelfRepository extends Neo4jRepository<Shelf, Long> {
}
