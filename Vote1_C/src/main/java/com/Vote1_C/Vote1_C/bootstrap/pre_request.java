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

import java.util.Objects;

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

    int page = 0;
    int reviewPage = 0;
    String response;
    String responseReview;

   @EventListener(ContextRefreshedEvent.class)
    public void run() throws JsonProcessingException {
       System.out.println(" [x] Requesting review from recovery system");
       do {
           String pageString = String.valueOf(page);
           response = (String) template.convertSendAndReceive(exchange.getName(), "rpc", "Vot"+pageString);
           if(response != null){
               voteService.updateDataBaseVote(response);
           }
           System.out.println(" [.] Got '" + response + "'");
           page++;
       }while (!Objects.equals(response, "[ ]") && !Objects.equals(response, null));
       System.out.println(" [x] Requesting product from recovery system");
       do {
           String pageString = String.valueOf(reviewPage);
           responseReview = (String) template.convertSendAndReceive(exchange.getName(), "rpc", "Rev"+pageString);
           if(responseReview != null){
               reviewService.updateDataBaseReview(responseReview);
           }
           System.out.println(" [.] Got '" + responseReview + "'");
           reviewPage++;
       }while (!Objects.equals(response, "[ ]") && !Objects.equals(response, null));
    }

}
