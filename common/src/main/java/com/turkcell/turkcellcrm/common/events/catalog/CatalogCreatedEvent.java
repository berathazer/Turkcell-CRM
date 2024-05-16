package com.turkcell.turkcellcrm.common.events.catalog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogCreatedEvent {
    private int catalogId;
    private String name;
}
