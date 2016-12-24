package com.skidata.x.order;

import com.skidata.x.order.command.Order;

/**
 * @author firoz
 * @since 24/12/16
 */
public enum OrderStatus {
    /**
     * Placed, but not payed yet. Still changeable.
     */
    PAYMENT_EXPECTED,

    /**
     * {@link Order} was payed. No changes allowed to it anymore.
     */
    PAID,

    /**
     * The {@link Order} is currently processed.
     */
    PREPARING,

    /**
     * The {@link Order} is ready to be picked up by the customer.
     */
    READY,

    /**
     * The {@link Order} was completed.
     */
    TAKEN;
}
