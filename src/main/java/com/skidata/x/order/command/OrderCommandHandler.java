package com.skidata.x.order.command;

import com.skidata.x.order.api.CreateOrder;
import com.skidata.x.order.api.PayForOrder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.LockAwareAggregate;
import org.axonframework.eventsourcing.EventSourcedAggregate;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author firoz
 * @since 04/01/17
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderCommandHandler {

    @NonNull
    private final EventSourcingRepository<Order> orderRepository;

    @CommandHandler
    public String createOrder(CreateOrder createOrder) throws Exception {
        log.info("Handling command = {}", createOrder.toString());
        return orderRepository.newInstance(() -> new Order(createOrder.getId(), createOrder.getOrderLines())).identifierAsString();
    }

    @CommandHandler
    public String payForOrder(PayForOrder payForOrder){
        log.info("Paying for order = {}", payForOrder.toString());
        Aggregate<Order> orderAggregate = orderRepository.load(payForOrder.getId());
        orderAggregate.execute(order ->
            order.pay()
        );
        return orderAggregate.identifierAsString();
    }
}
