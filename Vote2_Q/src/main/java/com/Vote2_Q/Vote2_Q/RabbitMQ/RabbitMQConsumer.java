package com.Vote2_Q.Vote2_Q.RabbitMQ;

import com.Vote2_Q.Vote2_Q.model.Vote;
import com.Vote2_Q.Vote2_Q.repositories.VoteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @Autowired
    private VoteRepository voteRepository;

    @RabbitListener(queues = "#{autoDeleteQueue.name}")
    public void consumeJsonMessage(String vote) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Vote vt = objectMapper.readValue(vote, Vote.class);
        voteRepository.save(vt);
        System.out.println("Creating new vote in Database with id:" + vt.getId());
    }

}
