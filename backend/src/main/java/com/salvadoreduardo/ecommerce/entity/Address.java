package com.salvadoreduardo.ecommerce.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cep;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private Integer number;

    public Address() {}

    public Address(String name) {
        this.name = name;
    }

    public Address(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
