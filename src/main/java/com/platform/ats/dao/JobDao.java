package com.platform.ats.dao;

import com.platform.ats.entities.Job;
import java.util.List;

public interface JobDao {
    Job createJob(Job job);

    List<Job> findByTitle(String title);

    List<Job> findAll(String keyword, int minSalary, int maxSalary);

    List<Job> findAll();

    Job updateJob(Job job);
}
