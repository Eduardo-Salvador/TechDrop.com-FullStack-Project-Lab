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
    private String cep;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String complement;
    private Integer number;

    public Address() {
    }

    public Address(String cep) {
        this.cep = cep;
    }

    public Address(String cep, String state, String city, String neighborhood, String street, String complement) {
        this.cep = cep;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.complement = complement;
    }

    public Address(String cep, String state, String city, String neighborhood, String street, String complement, Integer number) {
        this.cep = cep;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.complement = complement;
        this.number = number;
    }
}