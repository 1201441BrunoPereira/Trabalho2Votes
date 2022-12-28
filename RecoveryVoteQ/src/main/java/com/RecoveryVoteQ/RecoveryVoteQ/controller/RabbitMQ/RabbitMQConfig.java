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
    public FanoutExchange fanoutTempVote() {
        return new FanoutExchange("tempVote.created");
    }

    @Bean
    public FanoutExchange fanoutTempVoteDelete() {
        return new FanoutExchange("tempVote.deleted");
    }

    @Bean
    public FanoutExchange fanoutDeleteVote() {
        return new FanoutExchange("reviewVote.deleted");
    }

    @Bean
    public Queue autoDeleteQueue() {
        return new AnonymousQueue();
    }
    @Bean
    public Queue autoDeleteQueue2() {
        return new AnonymousQueue();
    }
    @Bean
    public Queue autoDeleteQueue3() {
        return new AnonymousQueue();
    }
    @Bean
    public Queue autoDeleteQueue4() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding(FanoutExchange fanout, Queue autoDeleteQueue) {
        return BindingBuilder.bind(autoDeleteQueue).to(fanout);
    }

    @Bean
    public Binding binding2(FanoutExchange fanoutTempVote, Queue autoDeleteQueue2) {
        return BindingBuilder.bind(autoDeleteQueue2).to(fanoutTempVote);
    }

    @Bean
    public Binding binding3(FanoutExchange fanoutTempVoteDelete, Queue autoDeleteQueue3) {
        return BindingBuilder.bind(autoDeleteQueue3).to(fanoutTempVoteDelete);
    }

    @Bean
    public Binding binding4(FanoutExchange fanoutDeleteVote, Queue autoDeleteQueue4) {
        return BindingBuilder.bind(autoDeleteQueue4).to(fanoutDeleteVote);
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
