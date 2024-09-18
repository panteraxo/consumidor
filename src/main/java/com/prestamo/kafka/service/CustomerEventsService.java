package com.prestamo.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.prestamo.kafka.config.Event;
import com.prestamo.kafka.entity.PaisCreatedEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomerEventsService {

	@KafkaListener(topics = "${topic.customer.name:topic-pais-3}",
			containerFactory = "kafkaListenerContainerFactory",
	groupId = "prueba")
	public void consumer(Event<?> event) {
		if (event.getClass().isAssignableFrom(PaisCreatedEvent.class)) {
			PaisCreatedEvent customerCreatedEvent = (PaisCreatedEvent) event;
			log.info("Received Customer created event .... with Id={}, data={}",
					customerCreatedEvent.getId(),
					customerCreatedEvent.getData().toString());
		}

	}
	
	

}
