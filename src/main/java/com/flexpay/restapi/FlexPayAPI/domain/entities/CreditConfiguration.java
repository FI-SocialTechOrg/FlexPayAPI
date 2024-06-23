package com.flexpay.restapi.FlexPayAPI.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "credit_configuration")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CreditConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "max_credit", nullable = false)
    private double maxCredit;

    @Column(name = "monthly_fee", nullable = false)
    private double monthlyFee;

    @Column(name = "discount", nullable = false)
    private double discount;

    @Column(name = "grace_period", nullable = false)
    private int gracePeriod;

    @Column(name = "grace_type", nullable = false)
    private int graceType;

    @Column(name = "initial_fee", nullable = false)
    private int initialFee;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "creditConfiguration")
    private List<Interest> interests;

}
