package com.Vote1_C.Vote1_C.repositories;

import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Repository
public class ReviewRepositoryHttp {

    public Boolean isApproved(String reviewId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8084/reviews/status/" + reviewId))
                .build();

        HttpResponse response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        var code = response.statusCode();
        if(code == 200){
            String statusReview = response.body().toString();
            if(statusReview.equals("APPROVED")) {
                return true;
            }
        }
        return false;
    }
}
