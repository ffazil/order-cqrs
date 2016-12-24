package com.skidata.x.order.event;

import com.skidata.x.order.command.OrderLine;
import com.skidata.x.order.OrderStatus;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author firoz
 * @since 25/12/16
 */
@Getter
@ToString
public class OrderCreated {
    private final String id;
    private final Set<OrderLine> orderLines = new HashSet<>();
    private final LocalDateTime orderedDate;
    private final OrderStatus status = OrderStatus.PAYMENT_EXPECTED;

    public OrderCreated(String id, Collection<OrderLine> orderLines){
        this.id = id;
        this.orderLines.addAll(orderLines);
        this.orderedDate = LocalDateTime.now();
    }
}
