package com.tsk.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OrderId;

    @ManyToOne
    @JsonIgnore
    private UserEntity userEntity;

    @OneToMany
    private Collection<OrderLine> orderLines = new ArrayList<>();

    private Boolean validated;

    @ManyToOne
    private PaymentMethod paymentMethod;

    @ManyToOne
    private Contact contact;

    private Double total;


    @CreatedDate
    private Date createdAt;
}
