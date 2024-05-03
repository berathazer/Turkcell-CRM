package com.turkcell.turkcellcrm.customerService.kafka.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class DeleteCustomerEvent {
    private int id;
}
