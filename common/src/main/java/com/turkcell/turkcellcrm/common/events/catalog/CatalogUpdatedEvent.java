package com.turkcell.turkcellcrm.common.events.catalog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogUpdatedEvent {

    private int id;
    private String catalogName;
}
