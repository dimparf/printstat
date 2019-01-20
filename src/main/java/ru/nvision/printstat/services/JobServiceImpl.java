package ru.nvision.printstat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.nvision.printstat.dal.repository.JobRepository;
import ru.nvision.printstat.model.Job;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Async
    @Override
    public void saveJobs(List<Job> jobList, long timestamp) {
        jobList.forEach(job -> job.setTime(timestamp));
        jobRepository.saveAll(jobList);
    }
}
