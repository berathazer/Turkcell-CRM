package com.turkcell.turkcellcrm.customerService.business.messages;

import org.apache.kafka.common.protocol.types.Field;

public class CustomerMessages {
    public static final String NATIONALITY_ID_CAN_NOT_DUPLICATE = "A customer is already exist with this Nationality ID";
    public static final String CUSTOMER_DOES_NOT_EXIST = "CUSTOMER DOES NOT EXIST FOR THIS ID.";
}
