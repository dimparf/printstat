package ru.nvision.printstat.services;

import ru.nvision.printstat.model.Job;
import ru.nvision.printstat.model.Statistics;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StatisticsService {

    List<Statistics> computeStatistics(
            Optional<String> user,
            Optional<String> type,
            Optional<String> device,
            long timeFrom,
            long timeTo);
    Map<String, Integer> aggregateDataForSession(List<Job> printJobs, long timestamp);

}
