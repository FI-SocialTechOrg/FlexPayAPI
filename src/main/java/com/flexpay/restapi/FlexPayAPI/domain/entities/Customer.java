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
@Table(name = "customer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstName", length = 150, nullable = false)
    private String firstName;

    @Column(name = "lastName", length = 150, nullable = false)
    private String lastName;

    @Column(name = "phone", length = 9, nullable = false)
    private String phone;

    @Column(name = "gender", length = 15, nullable = false)
    private String gender;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "dni", length = 8, nullable = false)
    private String dni;

    @Column(name = "photoUrl", length = 8, nullable = false)
    private String photoUrl;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
