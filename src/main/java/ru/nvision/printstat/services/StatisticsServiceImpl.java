package ru.nvision.printstat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import ru.nvision.printstat.model.Job;
import ru.nvision.printstat.model.Statistics;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public StatisticsServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Statistics> computeStatistics(
            Optional<String> maybeUser,
            Optional<String> maybeType,
            Optional<String> maybeDevice,
            long timeFrom,
            long timeTo) {
        Query query = new Query();
        maybeUser.ifPresent(user -> query.addCriteria(where("user").is(user)));
        maybeType.ifPresent(type -> query.addCriteria(where("type").is(type)));
        maybeDevice.ifPresent(device -> query.addCriteria(where("device").is(device)));
        if (timeFrom < timeTo && timeFrom != 0 && timeTo != 0) {
            query.addCriteria(where("time").gte(timeFrom).lte(timeTo));
        }
        query.with(new Sort(Sort.Direction.ASC, "time"));
        return mongoTemplate.find(query, Job.class).stream().map(Statistics::fromJob).collect(Collectors.toList());
    }

    @Override
    public Map<String, Integer> aggregateDataForSession(List<Job> printJobs, long timestamp) {
        return printJobs.stream().collect(groupingBy(Job::getUser, summingInt(Job::getAmount)));
    }

}
