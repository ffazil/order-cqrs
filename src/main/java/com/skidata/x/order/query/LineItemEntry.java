package com.skidata.x.order.query;

import com.skidata.x.order.Milk;
import com.skidata.x.order.Size;
import com.skidata.x.order.query.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.money.MonetaryAmount;
import javax.persistence.Entity;

/**
 * @author firoz
 * @since 25/12/16
 */
@Data
@Entity
@NoArgsConstructor(force = true)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LineItemEntry extends AbstractEntity {

    private final String name;
    private final int quantity;
    private final Milk milk;
    private final Size size;
    private final MonetaryAmount price;

    public LineItemEntry(String name, MonetaryAmount price) {
        this(name, 1, Milk.SEMI, Size.LARGE, price);
    }
}
