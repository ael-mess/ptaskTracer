package com.task;

import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class task {
    protected Integer id = null;
    protected Integer name = null;
    protected Color color = null;
    protected Integer cpu_id = null;
    protected Integer period = null;
    protected Integer deadline = null;
    protected Double start = null;
    protected Boolean state = false;
    protected List<t_event> events = null;

    public Integer getId() {
        return this.id;
    }

    public Integer getName() {
        return this.name;
    }

    public Color getColor() {
        return this.color;
    }

    public Integer getCpu_id() {
        return this.cpu_id;
    }

    public Integer getPeriod() {
        return this.period;
    }

    public Integer getDeadline() {
        return this.deadline;
    }

    public Double getStart() {
        return this.start;
    }

    public Boolean isActivated() {
        return this.state;
    }

    public List<t_event> getEvents() {
        return this.events;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public void setColor() {
        Random randomGenerator = new Random();
        int r = randomGenerator.nextInt(256);
        int g = randomGenerator.nextInt(256);
        int b = randomGenerator.nextInt(256);
        this.color = (new Color(r,g,b)).darker();
    }

    public void setCpu_id(Integer id) {
        this.cpu_id = id;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }

    public void setStart(Double start) {
        this.start = start;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public void setEvents(List<t_event> events) {
        this.events = events;
    }

    public String description() {
        String ret = "id: "+this.getId()+" idx: "+this.getName()+" ("+this.getColor().toString()+this.isActivated()+") cpu id="+this.getCpu_id()+" periode="+this.getPeriod()+" deadline="+this.getDeadline()+" start="+this.getStart()+"\n";
        ret += "[ ";
        for(t_event ev : this.events) ret += ev.description()+", ";
        ret += "]";
        return ret;
    }
}
