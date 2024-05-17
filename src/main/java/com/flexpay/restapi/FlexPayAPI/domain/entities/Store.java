package com.flexpay.restapi.FlexPayAPI.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "firstName", length = 150, nullable = false)
    private String firstName;

    @Column(name = "lastName", length = 150, nullable = false)
    private String lastName;

    @Column(name = "phone", length = 9, nullable = false)
    private String phone;

    @Column(name = "dni", length = 8, nullable = false)
    private String dni;

    @Column(name = "ruc", length = 11, nullable = false)
    private String ruc;

    @Column(name = "companyName", length = 150, nullable = false)
    private String companyName;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
