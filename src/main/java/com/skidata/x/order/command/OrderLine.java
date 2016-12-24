package com.skidata.x.order.command;

import com.skidata.x.order.Milk;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;

/**
 * @author firoz
 * @since 24/12/16
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class OrderLine {

    private final int quantity;
    private final Milk milk;
    private final String name;
    private final MonetaryAmount price;

    public OrderLine(String name, MonetaryAmount price) {
        this(1, Milk.SEMI, name, Money.of(4.20, "EURO"));
    }




}
