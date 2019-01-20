package ru.nvision.printstat.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Document
@XmlRootElement(name = "jobs")
public class Job {
    private int id;
    private String type;
    private String user;
    private String device;
    private int amount;
    private long time;

    public Job() {
    }

    public Job(int id, String type, String user, String device, int amount, long time) {
        this.id = id;
        this.type = type;
        this.user = user;
        this.device = device;
        this.amount = amount;
        this.time = time;
    }

    @XmlAttribute(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("type", type)
                .append("user", user)
                .append("device", device)
                .append("amount", amount)
                .append("time", time)
                .toString();
    }
}
