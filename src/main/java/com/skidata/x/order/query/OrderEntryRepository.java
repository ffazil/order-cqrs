package com.skidata.x.order.query;

import com.skidata.x.order.query.OrderEntry;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author firoz
 * @since 25/12/16
 */
public interface OrderEntryRepository extends JpaRepository<OrderEntry, Long>{
}
