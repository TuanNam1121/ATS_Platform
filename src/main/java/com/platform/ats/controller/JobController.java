package com.platform.ats.controller;

import com.platform.ats.dto.JobRequest;
import com.platform.ats.dto.JobSearchDTO;
import com.platform.ats.dto.SkillResponse;
import com.platform.ats.entities.Department;
import com.platform.ats.entities.Job;
import com.platform.ats.services.DepartmentService;
import com.platform.ats.services.JobService;
import com.platform.ats.services.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;
    private final DepartmentService departmentService;
    private final SkillService skillService;

    @GetMapping("/detail")
    public ModelAndView showCreateJob() {
        List<Department> departments = departmentService.findAll();
        List<SkillResponse> skills = skillService.findAll();

        ModelAndView mv = new ModelAndView("jobs/job_detail");
        mv.setViewName("jobs/job_detail");
        mv.addObject("departments", departments);
        mv.addObject("skills", skills);
        mv.addObject("job", new JobRequest());
        mv.addObject("message", "");

        System.out.println("Skills:" + skills);

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createJob(@ModelAttribute(name = "job") JobRequest jobRequest, Model model) {
        System.out.println("Create job" + jobRequest);

        jobService.createJob(jobRequest);
        model.addAttribute("message", "Create a new job successful");

        return "/jobs/job_detail";
    }

    @GetMapping
    public String listAll(@ModelAttribute(name = "search") JobSearchDTO param, Model model) {
        List<Job> jobs = jobService.findAll(param);
        System.out.println(param);
        model.addAttribute("jobs", jobs);
        return "jobs/general_dashboard";
    }

    @GetMapping(path = "/{id}")
    public String getById() {
        return null;
    }
}
