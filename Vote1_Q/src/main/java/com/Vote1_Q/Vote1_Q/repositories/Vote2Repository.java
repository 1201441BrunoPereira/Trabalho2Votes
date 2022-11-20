package com.Vote1_Q.Vote1_Q.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

@Repository
public class Vote2Repository {

    public int getTotalVotesByReviewId(String reviewId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8085/votes/" + reviewId))
                .build();

        HttpResponse response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        var code = response.statusCode();
        if(code == 200){
            ObjectMapper objectMapper = new ObjectMapper();
            String body = response.body().toString();
            int totalVotes = objectMapper.readValue(body, int.class);
            return totalVotes;
        }
        return 0;
    }

    public boolean existVote(String reviewId, Long userId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8085/votes/" + reviewId + "/" + userId))
                .build();

        HttpResponse response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        var code = response.statusCode();
        return code == 404;
    }
}
