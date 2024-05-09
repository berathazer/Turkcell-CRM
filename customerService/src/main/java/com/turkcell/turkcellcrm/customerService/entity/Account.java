package com.turkcell.turkcellcrm.customerService.entity;

import com.turkcell.turkcellcrm.customerService.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {
    private String accountNumber;
    private String accountName;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(mappedBy = "account")
    private AccountAddress accountAddress;

    @ManyToOne
    @JoinColumn(name = "account_type_id")
    private AccountType accountType;
}
