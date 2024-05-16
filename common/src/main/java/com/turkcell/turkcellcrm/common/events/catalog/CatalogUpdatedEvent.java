package com.turkcell.turkcellcrm.common.events.catalog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogUpdatedEvent {
    private int catalogId;
    private String name;
}
