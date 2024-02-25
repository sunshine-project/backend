package com.example.sunshineserver.saramin.application;

import com.example.sunshineserver.saramin.presentation.dto.Job;
import com.example.sunshineserver.saramin.presentation.dto.JobsContainer;
import com.example.sunshineserver.saramin.presentation.dto.SaraminJobRequest;
import com.example.sunshineserver.saramin.presentation.dto.SaraminJobResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class SaraminService {

    @Value("${saramin.access-key}")
    private String accessKey;

    // open source library for inquiring job information
    private final String baseUrl = "https://oapi.saramin.co.kr/job-search";

    public List<SaraminJobResponse> inquire(SaraminJobRequest request) {
        WebClient webClient = WebClient.create(baseUrl);

        Mono<JobsContainer> mono = webClient.get()
            .uri(uriBuilder -> uriBuilder
	.queryParam("access-key", accessKey)
	.queryParam("keywords", request.keyword())
	.queryParam("ind_cd", request.ind_cd())
	.build())
            .retrieve()
            .bodyToMono(JobsContainer.class);

        ArrayList<SaraminJobResponse> responses = new ArrayList<>();
        for (Job job : mono.block().getJobs().getJobList()) {
            responses.add(SaraminJobResponse.of(job, request.ind_cd()));
            System.out.println(SaraminJobResponse.of(job, request.ind_cd()).toString());
        }
        return responses;
    }
}
