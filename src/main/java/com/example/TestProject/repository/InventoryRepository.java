package com.example.TestProject.repository;

import com.example.TestProject.model.Inventory;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends Neo4jRepository<Inventory, Integer> {
}
