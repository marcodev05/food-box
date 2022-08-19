package com.tsk.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OrderId;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<OrderLine> orderLines = new ArrayList<>();

    private Boolean validated;

    @ManyToOne
    @JoinColumn(name = "payment_code")
    private PaymentMethod paymentMethod;

    private Double total;

    @CreatedDate
    private Instant createdAt;
}
