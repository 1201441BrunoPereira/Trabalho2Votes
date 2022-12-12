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
    public FanoutExchange fanoutReviewCreated() {
        return new FanoutExchange("review1.created");
    }

    @Bean
    public FanoutExchange fanoutReviewDeleted() {
        return new FanoutExchange("review1.deleted");
    }

    @Bean
    public FanoutExchange fanoutReviewApproved() {
        return new FanoutExchange("review1.changedStatus");
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
    public FanoutExchange fanoutReviewCreated2() {
        return new FanoutExchange("review2.created");
    }

    @Bean
    public FanoutExchange fanoutReviewDeleted2() {
        return new FanoutExchange("review2.deleted");
    }

    @Bean
    public FanoutExchange fanoutReviewApproved2() {
        return new FanoutExchange("review2.changedStatus");
    }

    @Bean
    public Queue autoDeleteQueue4() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue autoDeleteQueue5() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue autoDeleteQueue6() {
        return new AnonymousQueue();
    }


    @Bean
    public Binding binding4(FanoutExchange fanoutReviewCreated2, Queue autoDeleteQueue4) {
        return BindingBuilder.bind(autoDeleteQueue4).to(fanoutReviewCreated2);
    }

    @Bean
    public Binding binding5(FanoutExchange fanoutReviewDeleted2, Queue autoDeleteQueue5) {
        return BindingBuilder.bind(autoDeleteQueue5).to(fanoutReviewDeleted2);
    }

    @Bean
    public Binding binding6(FanoutExchange fanoutReviewApproved2, Queue autoDeleteQueue6) {
        return BindingBuilder.bind(autoDeleteQueue6).to(fanoutReviewApproved2);
    }
}
