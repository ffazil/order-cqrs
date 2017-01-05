package com.skidata.x.order.event;

import com.skidata.x.order.command.OrderLine;
import com.skidata.x.order.OrderStatus;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author firoz
 * @since 25/12/16
 */
@Value
public class OrderCreated {
    private String id;
    private Set<OrderLine> orderLines = new HashSet<>();
    private LocalDateTime orderedDate;
    private OrderStatus status;

    public OrderCreated(String id, Collection<OrderLine> orderLines){
        this.id = id;
        this.orderLines.addAll(orderLines);
        this.orderedDate = LocalDateTime.now();
        status = OrderStatus.PAYMENT_EXPECTED;
    }
}
