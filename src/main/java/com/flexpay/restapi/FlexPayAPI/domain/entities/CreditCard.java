package com.flexpay.restapi.FlexPayAPI.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "credit_card")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "max_credit", nullable = false)
    private double maxCredit;

    @Column(name = "initial_credit", nullable = false)
    private double initialCredit;

    @Column(name = "balance", nullable = false)
    private double balance;

    @Column(name = "purchase", nullable = false)
    private double purchase;

    @Column(name = "purchase_interest", nullable = false)
    private double purchaseInterest;

    @Column(name = "debt", nullable = false)
    private double debt;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pay_card_id", nullable = false)
    private PayCard payCard;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_card_id", nullable = false)
    private StateCard stateCard;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_cart_id", nullable = false)
    private ShoppingCart shoppingCart;

}
