package com.skidata.x.order.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skidata.x.order.Milk;
import lombok.*;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;

/**
 * @author firoz
 * @since 24/12/16
 */
@Getter
@NoArgsConstructor(force = true)
@EqualsAndHashCode
public class OrderLine {

    private final int quantity;
    private final String name;
    private final MonetaryAmount price;

    public OrderLine(String name) {
        this.quantity = 1;
        this.name = name;
        this.price = Money.of(4.20, "EUR");
    }




}
