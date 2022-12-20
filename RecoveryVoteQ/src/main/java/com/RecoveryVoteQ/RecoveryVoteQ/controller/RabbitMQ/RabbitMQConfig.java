package com.RecoveryVoteQ.RecoveryVoteQ.controller.RabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("vote.created");
    }

    @Bean
    public Queue autoDeleteQueue() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding(FanoutExchange fanout, Queue autoDeleteQueue) {
        return BindingBuilder.bind(autoDeleteQueue).to(fanout);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("voteQRecovery.request");
    }

    @Bean
    public Queue queue() {
        return new Queue("voteQRecovery.request");
    }

    @Bean
    public Binding binding6(DirectExchange exchange,
                            Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("rpc");
    }
}
