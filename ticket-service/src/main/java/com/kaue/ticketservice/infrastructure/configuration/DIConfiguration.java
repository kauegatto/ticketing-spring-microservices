package com.kaue.ticketservice.infrastructure.configuration;

import com.kaue.ticketservice.domain.ports.TicketRepository;
import com.kaue.ticketservice.domain.services.Notifier;
import com.kaue.ticketservice.domain.services.TicketService;
import com.kaue.ticketservice.infrastructure.amqp.RabbitTicketPublisher;
import com.kaue.ticketservice.infrastructure.properties.TicketQueueProperties;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class DIConfiguration {
  private TicketRepository ticketRepository;
  private TicketQueueProperties ticketQueueProperties;
  private RabbitTemplate rabbitTemplate;
  @Bean
  public TicketService ticketService() {
    return new TicketService(ticketRepository, ticketsMessagePublisher());
  }
  @Bean
  public Notifier ticketsMessagePublisher(){
    return new RabbitTicketPublisher(ticketQueueProperties, rabbitTemplate);
  }
}
