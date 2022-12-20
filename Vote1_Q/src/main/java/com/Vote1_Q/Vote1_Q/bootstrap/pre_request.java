package com.Vote1_Q.Vote1_Q.bootstrap;


import com.Vote1_Q.Vote1_Q.service.VoteService;
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
    }

}
