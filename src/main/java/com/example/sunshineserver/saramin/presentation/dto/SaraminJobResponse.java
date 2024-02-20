package com.example.sunshineserver.saramin.presentation.dto;

public record SaraminJobResponse(String companyName,
		 String industry,
		 String location,
		 String jobType,
		 String url,
		 int ind_cd) {

    public static SaraminJobResponse of(Job job, int ind_cd) {
        return new SaraminJobResponse(job.getCompany().getDetail().getCompanyName(),
            job.getPosition().getIndustry().getName(),
            job.getPosition().getLocation().getName(),
            job.getPosition().getJobType().getName(),
            job.getUrl(),
            ind_cd);
    }

    @Override
    public String toString() {
        return "SaraminJobResponse{" +
            "companyName='" + companyName + '\'' +
            ", industry='" + industry + '\'' +
            ", location='" + location + '\'' +
            ", jobType='" + jobType + '\'' +
            ", url='" + url + '\'' +
            ", ind_cd=" + ind_cd +
            '}';
    }
}
