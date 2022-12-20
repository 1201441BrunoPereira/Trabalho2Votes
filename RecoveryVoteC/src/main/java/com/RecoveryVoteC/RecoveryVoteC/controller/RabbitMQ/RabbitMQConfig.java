package com.RecoveryVoteC.RecoveryVoteC.controller.RabbitMQ;

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
    public FanoutExchange fanoutReviewCreated() {
        return new FanoutExchange("review.created");
    }

    @Bean
    public FanoutExchange fanoutReviewDeleted() {
        return new FanoutExchange("review.deleted");
    }

    @Bean
    public FanoutExchange fanoutReviewApproved() {
        return new FanoutExchange("review.changedStatus");
    }

    @Bean
    public Queue autoDeleteQueue1() {
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
    public Binding binding1(FanoutExchange fanoutReviewCreated, Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1).to(fanoutReviewCreated);
    }

    @Bean
    public Binding binding2(FanoutExchange fanoutReviewDeleted, Queue autoDeleteQueue2) {
        return BindingBuilder.bind(autoDeleteQueue2).to(fanoutReviewDeleted);
    }

    @Bean
    public Binding binding3(FanoutExchange fanoutReviewApproved, Queue autoDeleteQueue3) {
        return BindingBuilder.bind(autoDeleteQueue3).to(fanoutReviewApproved);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("voteCRecovery.request");
    }

    @Bean
    public Queue queue() {
        return new Queue("voteCRecovery.request");
    }

    @Bean
    public Binding binding6(DirectExchange exchange,
                            Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("rpc");
    }
}
