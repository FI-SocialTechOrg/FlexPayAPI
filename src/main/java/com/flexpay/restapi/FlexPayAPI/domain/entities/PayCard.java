package com.flexpay.restapi.FlexPayAPI.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pay_card")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PayCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "pay_amount", nullable = false)
    private double payAmount;

    @Column(name = "date_limit", nullable = false)
    private LocalDate dateLimit;

    @OneToMany(mappedBy = "payCard")
    private List<PaymentMethod> paymentMethods;
}
