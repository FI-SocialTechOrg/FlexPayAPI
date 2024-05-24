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
@Table(name = "store")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", length = 150, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 150, nullable = false)
    private String lastName;

    @Column(name = "phone", length = 9, nullable = false)
    private String phone;

    @Column(name = "dni", length = 8, nullable = false)
    private String dni;

    @Column(name = "ruc", length = 11, nullable = false)
    private String ruc;

    @Column(name = "company_name", length = 150, nullable = false)
    private String companyName;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @OneToMany(mappedBy = "store")
    private List<ProductStock> productStocks;
}
