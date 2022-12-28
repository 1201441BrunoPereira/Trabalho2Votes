package com.Vote1_Q.Vote1_Q.bootstrap;


import com.Vote1_Q.Vote1_Q.service.TemporaryVoteService;
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
    TemporaryVoteService temporaryVoteService;

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange exchange;

    int page = 0;
    int tempVotePage = 0;

    String responseTempVote;
    String response;

    @EventListener(ContextRefreshedEvent.class)
    public void run() throws JsonProcessingException {
        System.out.println(" [x] Requesting vote from recovery system");
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
            String pageString = String.valueOf(tempVotePage);
            responseTempVote = (String) template.convertSendAndReceive(exchange.getName(), "rpc", "Tev"+pageString);
            if(responseTempVote != null){
                temporaryVoteService.updateDataBaseTempVote(responseTempVote);
            }
            System.out.println(" [.] Got '" + responseTempVote + "'");
            tempVotePage++;
        }while (!Objects.equals(response, "[ ]") && !Objects.equals(response, null));
    }
}
