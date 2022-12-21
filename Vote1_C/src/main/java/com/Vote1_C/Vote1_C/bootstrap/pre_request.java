package com.Vote1_C.Vote1_C.bootstrap;


import com.Vote1_C.Vote1_C.service.ReviewService;
import com.Vote1_C.Vote1_C.service.VoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class pre_request {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange exchange;

   @EventListener(ContextRefreshedEvent.class)
    public void run() throws JsonProcessingException {
       System.out.println(" [x] Requesting review from recovery system");
       String response = (String) template.convertSendAndReceive(exchange.getName(), "rpc", "Vote");
       voteService.updateDataBaseVote(response);
       System.out.println(" [.] Got '" + response + "'");
       System.out.println(" [x] Requesting product from recovery system");
       String responseReview = (String) template.convertSendAndReceive(exchange.getName(), "rpc", "Review");
       reviewService.updateDataBaseReview(responseReview);
       System.out.println(" [.] Got '" + responseReview + "'");
    }

}
