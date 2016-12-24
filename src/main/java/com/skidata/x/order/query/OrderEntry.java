package com.skidata.x.order.query;

import com.skidata.x.order.OrderStatus;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author firoz
 * @since 25/12/16
 */
@Entity
@Getter
@ToString(exclude = "lineItems")
@Table(name = "OCOrder")
public class OrderEntry extends AbstractEntity {

    private final LocalDateTime orderedDate;
    private OrderStatus status;

    @OrderColumn //
    @Column(unique = true) //
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //
    private final List<LineItemEntry> lineItems = new ArrayList<>();


    public OrderEntry(Collection<LineItemEntry> lineItems, OrderStatus status, LocalDateTime orderedDate) {

        this.status = status;
        this.lineItems.addAll(lineItems);
        this.orderedDate = orderedDate;
    }
}
