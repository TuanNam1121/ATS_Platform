package com.platform.ats.services;

import com.platform.ats.dto.JobRequest;
import com.platform.ats.dto.JobSearchDTO;
import com.platform.ats.entities.Job;

import java.util.List;

public interface JobService {
    Job createJob(JobRequest jobRequest);

    List<Job> findByTitle(String title);

    List<Job> findAll(JobSearchDTO search);
}
