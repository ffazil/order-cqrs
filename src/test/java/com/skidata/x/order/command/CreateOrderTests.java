package com.skidata.x.order.command;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

/**
 * @author firoz
 * @since 03/01/17
 */
@Slf4j
@RunWith(SpringRunner.class)
@JsonTest
public class CreateOrderTests {

    @Autowired
    private JacksonTester<CreateOrder> tester;

    @Test
    public void serializes() throws IOException {
        OrderLine slimMilk = new OrderLine("Slim milk");
        OrderLine wholeMilk = new OrderLine("Whole milk");

        CreateOrder createOrder = new CreateOrder(Arrays.asList(slimMilk, wholeMilk));
        log.info("Serialized text = {}", this.tester.write(createOrder));




    }
}
