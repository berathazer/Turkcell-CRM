package com.turkcell.turkcellcrm.customerService.entity;

import com.turkcell.turkcellcrm.customerService.core.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {
    private int status;
    private String accountNumber;
    private String accountName;
    private String accountType;
    private String action;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
