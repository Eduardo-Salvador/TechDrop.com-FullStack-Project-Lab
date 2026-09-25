package com.salvadoreduardo.ecommerce.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;
    private BigDecimal price;

    public OrderItem() {}

    public OrderItem(Order order, Product product, Integer quantity, BigDecimal price) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }
}