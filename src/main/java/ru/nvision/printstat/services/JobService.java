package ru.nvision.printstat.services;

import ru.nvision.printstat.model.Job;

import java.util.List;

public interface JobService {
    void saveJobs(List<Job> jobList, long timestamp);
}
