package ru.nvision.printstat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nvision.printstat.dal.repository.JobRepository;
import ru.nvision.printstat.model.Job;
import ru.nvision.printstat.model.Statistics;
import ru.nvision.printstat.services.StatisticsServiceImpl;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("print")
public class ConsumePrinterDataController {

    private final StatisticsServiceImpl statisticsService;
    private final JobRepository jobRepository;

    @Autowired
    public ConsumePrinterDataController(StatisticsServiceImpl statisticsService, JobRepository jobRepository) {
        this.statisticsService = statisticsService;
        this.jobRepository = jobRepository;
    }

    @RequestMapping(
            value = "/jobs",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public ResponseEntity<Map<String, Integer>> consumePrinterData(@RequestBody List<Job> printData) {
        long timestamp = Instant.now().toEpochMilli();
        printData.forEach(job -> job.setTime(timestamp));
        jobRepository.saveAll(printData);
        return ResponseEntity.ok(statisticsService.aggregateDataForSession(printData, timestamp));
    }

    @RequestMapping(value = "/statistics", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<Statistics>> getStatistics(
            @RequestParam Optional<String> user,
            @RequestParam Optional<String> type,
            @RequestParam Optional<String> device,
            @RequestParam(defaultValue = "0", required = false) long timeFrom,
            @RequestParam(defaultValue = "0", required = false) long timeTo) {

        return ResponseEntity.ok(statisticsService.computeStatistics(user, type, device, timeFrom, timeTo));
    }

}
