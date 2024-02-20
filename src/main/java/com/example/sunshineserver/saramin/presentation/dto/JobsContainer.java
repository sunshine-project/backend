package com.example.sunshineserver.saramin.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;

@Getter
public class JobsContainer {

    private Jobs jobs;

    @Getter
    public static class Jobs {

        @JsonProperty("job")
        private List<Job> jobList;
    }
}