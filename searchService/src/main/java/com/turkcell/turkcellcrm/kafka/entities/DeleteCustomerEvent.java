package com.turkcell.turkcellcrm.kafka.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class DeleteCustomerEvent {
    private int id;
}
