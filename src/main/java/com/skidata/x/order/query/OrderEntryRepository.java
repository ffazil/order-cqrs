package com.skidata.x.order.query;

import com.skidata.x.order.query.OrderEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * @author firoz
 * @since 25/12/16
 */
@RepositoryRestResource
public interface OrderEntryRepository extends JpaRepository<OrderEntry, Long>{

    @Override
    @RestResource(exported = false)
    OrderEntry save(OrderEntry orderEntry);

    @Override
    @RestResource(exported = false)
    void delete(Long id);

    @Override
    @RestResource(exported = false)
    void delete(OrderEntry orderEntry);

    @Override
    @RestResource(exported = false)
    <S extends OrderEntry> List<S> save(Iterable<S> orderEntries);
}
