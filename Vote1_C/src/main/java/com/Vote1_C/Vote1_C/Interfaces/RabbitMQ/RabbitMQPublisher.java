package com.Vote1_C.Vote1_C.Interfaces.RabbitMQ;

import com.Vote1_C.Vote1_C.VoteDTO;
import com.Vote1_C.Vote1_C.model.TemporaryVote;
import com.Vote1_C.Vote1_C.model.Vote;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQPublisher {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private FanoutExchange fanout;

    @Autowired
    private FanoutExchange fanoutTempVote;

    @Autowired
    private FanoutExchange fanoutTempVoteDelete;

    @Autowired
    private Queue queueTempVote;

    public void sendJsonMessage(Vote vote) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(vote);
        template.convertAndSend(fanout.getName(), "", json);
    }

    public void sendJsonMessageToReview(VoteDTO vote) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(vote);
        template.convertAndSend(queueTempVote.getName(), json);
    }

    public void sendJsonMessageToCreateTempVote(TemporaryVote vote) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(vote);
        template.convertAndSend(fanoutTempVote.getName(), "", json);
    }

    public void sendJsonMessageToDeleteTempVote(String tempVoteId) throws JsonProcessingException {
        template.convertAndSend(fanoutTempVoteDelete.getName(), "", tempVoteId);
    }

}
