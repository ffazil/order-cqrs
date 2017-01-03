package com.skidata.x.order.command;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author firoz
 * @since 25/12/16
 */
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
}
