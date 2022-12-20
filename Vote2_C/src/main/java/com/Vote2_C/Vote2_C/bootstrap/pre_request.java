package com.Vote2_C.Vote2_C.bootstrap;


import com.Vote2_C.Vote2_C.service.ReviewService;
import com.Vote2_C.Vote2_C.service.VoteService;
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

    //int start = 0;

   @EventListener(ContextRefreshedEvent.class)
    public void run() throws JsonProcessingException {
       System.out.println(" [x] Requesting review from recovery system");
       String response = (String) template.convertSendAndReceive(exchange.getName(), "rpc", "Vote");
       voteService.updateDataBaseVote(response);
       System.out.println(" [.] Got '" + response + "'");
       System.out.println(" [x] Requesting product from recovery system");
       String responseReview = (String) template.convertSendAndReceive(exchange.getName(), "rpc", "Product");
       reviewService.updateDataBaseReview(responseReview);
       System.out.println(" [.] Got '" + responseReview + "'");
    }

}
