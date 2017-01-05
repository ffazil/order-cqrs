package com.skidata.x.order;

import com.skidata.x.order.command.Order;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private EventStore eventStore;

	@Autowired
	private EventBus eventBus;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

/*	@Bean
	ServletRegistrationBean h2ServletRegistrationBean(){
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings("/console*//*");
		return registrationBean;
	}*/

	@Bean
	public EventSourcingRepository<Order> orderRepository() {
		EventSourcingRepository<Order> repository = new EventSourcingRepository<Order>(Order.class, eventStore);
		return repository;
	}

}
