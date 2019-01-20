package ru.nvision.printstat.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Statistics {
    private int jobId;
    private String type;
    private String user;
    private String device;
    private int amount;
    private String time;

    public Statistics() {
    }

    private Statistics(int jobId, String type, String user, String device, int amount, String time) {
        this.jobId = jobId;
        this.type = type;
        this.user = user;
        this.device = device;
        this.amount = amount;
        this.time = time;
    }

    public static Statistics fromJob(Job job) throws NumberFormatException {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String formatDate = dateFormat.format(new Date(job.getTime()));
        return new Statistics(
                job.getId(),
                job.getType(),
                job.getUser(),
                job.getDevice(),
                job.getAmount(),
                formatDate);
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("jobId", jobId)
                .append("type", type)
                .append("user", user)
                .append("device", device)
                .append("amount", amount)
                .append("time", time)
                .toString();
    }
}
