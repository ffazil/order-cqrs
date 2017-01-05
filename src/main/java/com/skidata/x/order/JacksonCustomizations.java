package com.skidata.x.order;

import java.io.IOException;
import java.util.Collection;
import java.util.regex.Pattern;

import javax.money.MonetaryAmount;
import javax.money.format.MonetaryFormats;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skidata.x.order.command.OrderLine;
import org.javamoney.moneta.Money;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.rest.webmvc.json.JsonSchema.JsonSchemaProperty;
import org.springframework.data.rest.webmvc.json.JsonSchemaPropertyCustomizer;
import org.springframework.data.util.TypeInformation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * @author firoz
 * @since 03/01/17
 */
@Configuration
public class JacksonCustomizations {

    @Bean
    public Module moneyModule() {
        return new MoneyModule();
    }

    @Bean
    public Module orderModule() {
        return new OrderModule();
    }


    static class OrderModule extends SimpleModule{
        public OrderModule(){
            //setMixInAnnotation(CreateOrder.class, CreateOrderMixin.class);
            setMixInAnnotation(OrderLine.class, OrderLineMixin.class);
        }

        @JsonAutoDetect(isGetterVisibility = JsonAutoDetect.Visibility.NONE)
        static abstract class CreateOrderMixin {

            @JsonCreator
            public CreateOrderMixin(@JsonProperty("orderLines") Collection<OrderLine> orderLines) {}
        }

        static abstract class OrderLineMixin {

            @JsonCreator
            public OrderLineMixin(@JsonProperty("name") String name) {}
        }
    }

    @SuppressWarnings("serial")
    static class MoneyModule extends SimpleModule {

        public MoneyModule() {

            addSerializer(MonetaryAmount.class, new MonetaryAmountSerializer());
            addValueInstantiator(MonetaryAmount.class, new MoneyInstantiator());
        }

        /**
         * A dedicated serializer to render {@link MonetaryAmount} instances as formatted {@link String}. Also implements
         * {@link JsonSchemaPropertyCustomizer} to expose the different rendering to the schema exposed by Spring Data REST.
         *
         * @author Oliver Gierke
         */
        static class MonetaryAmountSerializer extends StdSerializer<MonetaryAmount>
                implements JsonSchemaPropertyCustomizer {

            private static final Pattern MONEY_PATTERN;

            static {

                StringBuilder builder = new StringBuilder();

                builder.append("(?=.)^"); // Start
                builder.append("[A-Z]{3}?"); // 3-digit currency code
                builder.append("\\s"); // single whitespace character
                builder.append("(([1-9][0-9]{0,2}(,[0-9]{3})*)|[0-9]+)?"); // digits with optional grouping by "," every 3)
                builder.append("(\\.[0-9]{1,2})?$"); // End with a dot and two digits

                MONEY_PATTERN = Pattern.compile(builder.toString());
            }

            public MonetaryAmountSerializer() {
                super(MonetaryAmount.class);
            }

            /*
             * (non-Javadoc)
             * @see com.fasterxml.jackson.databind.ser.std.StdSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
             */
            @Override
            public void serialize(MonetaryAmount value, JsonGenerator jgen, SerializerProvider provider) throws IOException {

                if (value != null) {
                    jgen.writeString(MonetaryFormats.getAmountFormat(LocaleContextHolder.getLocale()).format(value));
                } else {
                    jgen.writeNull();
                }
            }

            /*
             * (non-Javadoc)
             * @see org.springframework.data.rest.webmvc.json.JsonSchemaPropertyCustomizer#customize(org.springframework.data.rest.webmvc.json.JsonSchema.JsonSchemaProperty, org.springframework.data.util.TypeInformation)
             */
            @Override
            public JsonSchemaProperty customize(JsonSchemaProperty property, TypeInformation<?> type) {
                return property.withType(String.class).withPattern(MONEY_PATTERN);
            }
        }

        static class MoneyInstantiator extends ValueInstantiator {

            /*
             * (non-Javadoc)
             * @see com.fasterxml.jackson.databind.deser.ValueInstantiator#getValueTypeDesc()
             */
            @Override
            public String getValueTypeDesc() {
                return MonetaryAmount.class.toString();
            }

            /*
             * (non-Javadoc)
             * @see com.fasterxml.jackson.databind.deser.ValueInstantiator#canCreateFromString()
             */
            @Override
            public boolean canCreateFromString() {
                return true;
            }

            /*
             * (non-Javadoc)
             * @see com.fasterxml.jackson.databind.deser.ValueInstantiator#createFromString(com.fasterxml.jackson.databind.DeserializationContext, java.lang.String)
             */
            @Override
            public Object createFromString(DeserializationContext context, String value) throws IOException {
                return Money.parse(value, MonetaryFormats.getAmountFormat(LocaleContextHolder.getLocale()));
            }
        }
    }
}
