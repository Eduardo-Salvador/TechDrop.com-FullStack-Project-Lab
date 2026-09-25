package com.salvadoreduardo.ecommerce.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}