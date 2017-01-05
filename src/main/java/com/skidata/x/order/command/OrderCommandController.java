package com.skidata.x.order.command;

import com.skidata.x.order.api.CreateOrder;
import com.skidata.x.order.api.PayForOrder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

/**
 * @author firoz
 * @since 25/12/16
 */
@Slf4j
@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderCommandController {



    @NonNull
    private final CommandGateway commandGateway;




    @PostMapping
    public CompletableFuture<String> createOrder(@RequestBody CreateOrder createOrder) {
        return commandGateway.send(createOrder);
    }

    @PostMapping(value = "/pay")
    public CompletableFuture<String> payForOrder(@RequestBody PayForOrder payForOrder){
        return commandGateway.send(payForOrder);
    }
}
