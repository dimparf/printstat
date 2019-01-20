package ru.nvision.printstat.dal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.nvision.printstat.model.Job;


public interface JobRepository extends MongoRepository<Job, String> { }
