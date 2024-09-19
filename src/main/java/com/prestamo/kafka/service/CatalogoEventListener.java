package com.prestamo.kafka.service;

import java.text.SimpleDateFormat;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.prestamo.entity.Catalogo;
import com.prestamo.kafka.config.Event;
import com.prestamo.kafka.entity.CatalogoCreateEvent;


@Component
public class CatalogoEventListener {

	
	@KafkaListener( topics = "${topic.customer.name:topic-catalogo-delavega}",
					containerFactory = "kafkaListenerContainerFactory",
					groupId = "escuchador-catalogo-delavega")
	public void consumer(Event<?> event) {
		System.out.println(">>1 Evento recibido: " + event);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		if (event.getClass().isAssignableFrom(CatalogoCreateEvent.class)) {
			
			System.out.println(">>2  Evento Catalogo creado");
			CatalogoCreateEvent objEvent = (CatalogoCreateEvent) event;
			
			String id = objEvent.getId();
			String tipoEvento = 	objEvent.getType().name();
			String fecha = sdf.format(objEvent.getDate());
			Catalogo objCatalogo = objEvent.getData();
			
			int idCatalogo = objCatalogo.getIdCatalogo();
			String description = objCatalogo.getDescripcion();
			
			
			System.out.println(">>3  ID: " + id);
			System.out.println(">>4  Tipo de evento: " + tipoEvento);
			System.out.println(">>5  Fecha: " + fecha);
			System.out.println(">>6  ID Catalogo: " + idCatalogo);
			System.out.println(">>7  Descripcion: " + description);
	
		}
		
	}
	
}