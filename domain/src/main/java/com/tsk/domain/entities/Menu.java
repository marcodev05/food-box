package com.tsk.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Menu {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    @Column(nullable = false)
    private String name;

    private String description;

    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    private String picture;

    private Boolean available;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn
    private Category category;

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<OrderLine> orderLines;

    @CreatedDate
    private Instant createdAt;

}
