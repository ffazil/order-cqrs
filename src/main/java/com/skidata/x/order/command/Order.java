package com.skidata.x.order.command;

import com.skidata.x.order.*;
import com.skidata.x.order.event.OrderCreated;
import com.skidata.x.order.event.OrderPreparing;
import com.skidata.x.order.event.PayForOrder;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Set;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * @author firoz
 * @since 24/12/16
 */
@Aggregate
public class Order {

    @AggregateIdentifier
    private String reference;
    private OrderStatus status;

    public Order(String reference, Set<OrderLine> orderLines){
        apply(new OrderCreated(reference, orderLines));
    }

    Order(){

    }

    public void prepare(){
        apply(new OrderPreparing());
    }

    public void pay(){
        apply(new PayForOrder());
    }

    @EventSourcingHandler
    protected void on(OrderCreated event) {
        this.reference = event.getId();
        this.status = OrderStatus.PAYMENT_EXPECTED;
    }

    @EventSourcingHandler
    public void on(OrderPreparing event){

    }

    @EventSourcingHandler
    public void on(PayForOrder event){
        this.status = OrderStatus.PAID;
    }


}
