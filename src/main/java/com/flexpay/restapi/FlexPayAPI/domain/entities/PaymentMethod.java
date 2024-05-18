package com.flexpay.restapi.FlexPayAPI.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "payment_method")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type", length = 50, nullable = false)
    private String type;

    @Column(name = "mount", nullable = false)
    private double mount;

    @Column(name = "card_number", length = 16, nullable = false)
    private String cardNumber;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pay_card_id", nullable = false)
    private PayCard payCard;
}
