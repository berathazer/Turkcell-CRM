package com.turkcell.turkcellcrm.common.events.catalog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogCreatedEvent {

    private int id;
    private String catalogName;
}
