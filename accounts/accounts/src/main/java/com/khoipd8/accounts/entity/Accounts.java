package com.khoipd8.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accounts extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_number")
    private Long accountNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="customer_id", nullable = false)
    private Customer customer;

    @Column(name="account_type", nullable = false, length = 100)
    private String accountType;

    @Column(name="branch_address", nullable = false, length = 200)
    private String branchAddress;
}
