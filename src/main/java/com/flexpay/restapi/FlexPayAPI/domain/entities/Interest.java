package com.flexpay.restapi.FlexPayAPI.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "interest")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "rate", nullable = false)
    private double rate;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_configuration_id", nullable = false)
    private CreditConfiguration creditConfiguration;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pay_interest_id", nullable = false)
    private PayInterest payInterest;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_interest_id", nullable = false)
    private TypeInterest typeInterest;

}
