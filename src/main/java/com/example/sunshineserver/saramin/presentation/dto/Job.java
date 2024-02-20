package com.example.sunshineserver.saramin.presentation.dto;

import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@Getter
@ToString
public class Job {

    private Company company;
    private Position position;
    private String url;

    @Getter
    public static class Company {

        private Detail detail;

        @Getter
        public static class Detail {

            @JsonProperty("name")
            private String companyName;
        }
    }

    @Getter
    public static class Position {

        @JsonProperty("industry")
        private Industry industry;
        @JsonProperty("location")
        private Location location;
        @JsonProperty("job-type")
        private JobType jobType;

        @Getter
        public static class Industry {

            @JsonProperty("name")
            private String name;
        }

        @Getter
        public static class Location {

            @JsonProperty("name")
            private String name;
        }

        @Getter
        public static class JobType {

            @JsonProperty("name")
            private String name;
        }
    }

    @Override
    public String toString() {
        return "\nJob{" +
            "company=" + company.getDetail().getCompanyName() +
            ", \nposition=" + position.getIndustry().getName() +
            ", \nlocation=" + position.getLocation().getName() +
            ", \njobType=" + position.getJobType().getName() +
            ", \nurl='" + url + '\'' +
            '}';
    }
}
