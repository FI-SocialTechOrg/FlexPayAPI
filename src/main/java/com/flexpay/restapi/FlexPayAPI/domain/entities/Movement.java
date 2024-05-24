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

    @Column(name = "capitalization_period", nullable = false)
    private int capitalizationPeriod;

    @Column(name = "interest_rate", nullable = false)
    private double interestRate;

    @Column(name = "credit_term", nullable = false)
    private int creditTerm;

    @Column(name = "monthly_fees", nullable = false)
    private int monthlyFees;

    @Column(name = "payment_day", nullable = false)
    private LocalDate paymentDay;

    @Column(name = "discount", nullable = false)
    private double discount;

    @Column(name = "grace_period", nullable = false)
    private double gracePeriod;

    @Column(name = "initial_fee", nullable = false)
    private double initialFee  ;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_card_id", nullable = false)
    private CreditCard creditCard;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interest_id", nullable = false)
    private Interest interest;
}
