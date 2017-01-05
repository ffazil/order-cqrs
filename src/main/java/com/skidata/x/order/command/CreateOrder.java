/*
package com.skidata.x.order.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

*/
/**
 * @author firoz
 * @since 24/12/16
 *//*

@Getter
@NoArgsConstructor(force = true)
public class CreateOrder {
    private String id;
    private Set<OrderLine> orderLines = new HashSet<>();


    public CreateOrder( Collection<OrderLine> orderLines){
        this.id = UUID.randomUUID().toString();
        this.orderLines.addAll(orderLines);
    }

    public CreateOrder(OrderLine... lines){
        this(Arrays.asList(lines));
    }
}
*/
