package com.skidata.x.order.api

import com.fasterxml.jackson.annotation.JsonInclude
import com.skidata.x.order.command.OrderLine
import org.axonframework.commandhandling.TargetAggregateIdentifier
import java.util.*

/**
 * @author firoz
 * @since 04/01/17
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
class CreateOrder(@TargetAggregateIdentifier val id: String = UUID.randomUUID().toString(), val orderLines: Set<OrderLine>)

@JsonInclude(JsonInclude.Include.NON_EMPTY)
class PayForOrder(@TargetAggregateIdentifier val id: String)