package org.ats.services;

import org.ats.dto.JobRequest;
import org.ats.dto.JobSearchDTO;
import org.ats.entities.Job;

import java.util.List;

public interface JobService {
    Job createJob(JobRequest jobRequest);

    List<Job> findByTitle(String title);

    List<Job> findAll(JobSearchDTO search);
}
