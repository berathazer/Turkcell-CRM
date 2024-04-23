package com.turkcell.turkcellcrm.customerService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE (1),
    FEMALE (2),
    OTHER(3);
    private final int value;
}
