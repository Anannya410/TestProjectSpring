package com.example.TestProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    @Id
    private Long id;
    private String name;
    private String deviceType;

    public Long getId(){
       return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getDeviceType(){
        return this.deviceType;
    }
}
