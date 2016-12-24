package com.skidata.x.order.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author firoz
 * @since 24/12/16
 */
@Getter
@AllArgsConstructor
public class CreateOrder {
    private String id;
    private Set<OrderLine> orderLines = new HashSet<>();


}
