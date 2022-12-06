package com.Vote1_C.Vote1_C.RabbitMQ;

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
    public FanoutExchange fanoutReviewApproved() {
        return new FanoutExchange("review.approved");
    }
    @Bean
    public FanoutExchange fanoutReviewCreated() {
        return new FanoutExchange("review.createForVotes");
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
    public Binding binding1(FanoutExchange fanoutReviewApproved, Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1).to(fanoutReviewApproved);
    }

    @Bean
    public Binding binding2(FanoutExchange fanoutReviewCreated, Queue autoDeleteQueue2) {
        return BindingBuilder.bind(autoDeleteQueue2).to(fanoutReviewCreated);
    }
}
