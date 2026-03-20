package io.github.rubensrabelo.project.mscommand.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQTopicConfig {

    public final String exchangeName = "beautiqueExchange";

    @Bean
    TopicExchange exchange(){
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Queue customerQueue(){
        return new Queue("customerQueue",true);
    }

    @Bean
    public Binding bindingCustomer(Queue customerQueue, TopicExchange exchange){
        return BindingBuilder.bind(customerQueue).to(exchange).with("customer.#");
    }

    @Bean
    public Queue beautyProcedureQueue(){
        return new Queue("beautyProcedureQueue",true);
    }

    @Bean
    public Binding bindingBeautyProcedures(Queue beautyProcedureQueue, TopicExchange exchange){
        return BindingBuilder.bind(beautyProcedureQueue).to(exchange).with("beautyProcedures.#");
    }


    @Bean
    public Queue appointmentQueue(){
      return new Queue("appointmentQueue",true);
    }

    @Bean
    public Binding bindingAppointments(Queue appointmentQueue, TopicExchange exchange){
        return BindingBuilder.bind(appointmentQueue).to(exchange).with("appointments.#");
    }
}
