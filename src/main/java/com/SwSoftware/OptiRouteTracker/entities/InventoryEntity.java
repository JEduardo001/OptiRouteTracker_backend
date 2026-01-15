package com.SwSoftware.OptiRouteTracker.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "inventory", indexes = {
        @Index(name = "indexName", columnList = "name")
})
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createDate;
    private String location;
    private Integer quantity;
    private Boolean active;
    private LocalDateTime disabledAt;
    @OneToMany(mappedBy = "inventory",orphanRemoval = true, cascade = {CascadeType.REMOVE})
    private Set<ProductEntity> products;
}
