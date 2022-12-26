package com.Vote1_Q.Vote1_Q.bootstrap;


import com.Vote1_Q.Vote1_Q.service.VoteService;
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
    private VoteService voteService;

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange exchange;

    int page = 0;
    String response;

    @EventListener(ContextRefreshedEvent.class)
    public void run() throws JsonProcessingException {
       System.out.println(" [x] Requesting review from recovery system");
        do {
            response = (String) template.convertSendAndReceive(exchange.getName(), "rpc", "Vote");
            if(response != null){
                voteService.updateDataBaseVote(response);
            }
            System.out.println(" [.] Got '" + response + "'");
            page++;
        }while (!Objects.equals(response, "[ ]"));
    }
}
