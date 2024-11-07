package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Double price;
    
    
}

//add
package com.example.model;

public class Product {
    private String name;

    public Product(String name) {
        this.name = name;
    }

    // GetterとSetter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

