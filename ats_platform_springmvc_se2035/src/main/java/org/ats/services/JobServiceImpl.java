package org.ats.services;

import lombok.RequiredArgsConstructor;
import org.ats.dao.JobDao;
import org.ats.dto.JobRequest;
import org.ats.dto.JobSearchDTO;
import org.ats.entities.*;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobDao jobDao;

    @Override
    public Job createJob(JobRequest jobRequest) {
        return jobDao.createJob(toEntity(jobRequest));
    }

    @Override
    public List<Job> findByTitle(String title) {
        return List.of();
    }

    @Override
    public List<Job> findAll(JobSearchDTO search) {
        List<Job> list = new ArrayList<>();
        String title = search.getTitle();
        if(title != null) list = jobDao.findAll("%" + title + "%", search.getMinSalary(), search.getMaxSalary());
        else list = jobDao.findAll(null, search.getMinSalary(), search.getMaxSalary());
        return list;
    }

    private Job toEntity(JobRequest jobRequest) {
        Set<JobSkill> jobSkills = jobRequest.getSkillIds().stream().map((skillId) -> {
            JobSkill jobSkill = new JobSkill();
            jobSkill.setSkill(Skill.builder().id(skillId).build()); // 1, 3

            return jobSkill;
        }).collect(Collectors.toSet());


        Job job = Job.builder()
                .id(jobRequest.getId())
                .title(jobRequest.getTitle())
                .deadline(OffsetDateTime.of(jobRequest.getDeadline(), LocalTime.now(), ZoneOffset.ofHours(7)))
                .description(jobRequest.getDescription())
                .location(jobRequest.getLocation())
                .maxSalary(jobRequest.getMaxSalary())
                .minSalary(jobRequest.getMinSalary())
                .department(Department.builder().id(jobRequest.getDepartmentId()).build())
                .status(JobStatus.DRAFT.toString())
                .build();

        for (JobSkill jobSkill : jobSkills) {
            jobSkill.setJob(job);
        }

        job.setSkills(jobSkills);

        return job;
    }
}
