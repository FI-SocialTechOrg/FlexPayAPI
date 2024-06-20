package com.flexpay.restapi.FlexPayAPI.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pay_card")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PayMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "pay_amount", nullable = false)
    private double payAmount;

    @Column(name = "date_limit", nullable = false)
    private LocalDate dateLimit;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movement_id", nullable = false)
    private Movement movement;
}
