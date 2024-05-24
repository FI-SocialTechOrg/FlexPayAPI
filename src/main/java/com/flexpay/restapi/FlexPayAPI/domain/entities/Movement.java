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
@Table(name = "movement")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "credit_term", nullable = false)
    private int creditTerm;

    @Column(name = "payment_day", nullable = false)
    private LocalDate paymentDay;

    @Column(name = "date_movement", nullable = false)
    private LocalDate dateMovement;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_card_id", nullable = false)
    private CreditCard creditCard;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_configuration_id", nullable = false)
    private CreditConfiguration creditConfiguration;

}
