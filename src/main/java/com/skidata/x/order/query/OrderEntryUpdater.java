package com.skidata.x.order.query;

import com.skidata.x.order.event.OrderCreated;
import com.skidata.x.order.query.LineItemEntry;
import com.skidata.x.order.query.OrderEntry;
import com.skidata.x.order.query.OrderEntryRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author firoz
 * @since 25/12/16
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderEntryUpdater {

    private final OrderEntryRepository orderEntryRepository;

    @EventHandler
    public void on(OrderCreated created){
        Set<LineItemEntry> lineItems = new HashSet<>();
        created.getOrderLines().forEach(orderLine -> {
            lineItems.add(new LineItemEntry(orderLine.getName(), orderLine.getPrice()));
        });
        orderEntryRepository.save(new OrderEntry(lineItems));
    }
}
