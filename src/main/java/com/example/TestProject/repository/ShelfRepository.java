package com.example.TestProject.repository;

import com.example.TestProject.model.Shelf;
import com.example.TestProject.model.ShelfDTO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface ShelfRepository extends Neo4jRepository<Shelf, Long> {
    @Query("MATCH (s: Shelf) "
    + "WHERE s.id = $id "
    + "OPTIONAL MATCH (s) <-[:HAS]- (sp: ShelfPosition) "
    + "RETURN s AS shelf, sp.id AS shelfPositionId, sp.name AS shelfPositionName")
    Optional<ShelfDTO> findByIdCustom(Long id);

    @Query("MATCH (s: Shelf) "
    + "OPTIONAL MATCH (s) <-[:HAS]- (sp: ShelfPosition) "
    + "RETURN s AS shelf, sp.id AS shelfPositionId, sp.name AS shelfPositionName "
    + "ORDER BY s.id")
    List<ShelfDTO> findAllCustom();
}
