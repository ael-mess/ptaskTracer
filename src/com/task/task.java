/****************************************************************************
* Class:       task()                                                        *
* Parameters:  all the task thread parameters                                *
* Autor:       ael-mess                                                      *
* Description: represents the parameters required for the tasks thread       *
****************************************************************************/

package com.task;

import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

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

    public String getColorS() {
        return "rgb("+this.color.getRed()+","+this.color.getGreen()+","+this.color.getBlue()+")";
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
        String ret = "id: "+this.getId()+" idx: "+this.getName()+" ("+this.getColor().toString()+this.isActivated()+") cpu id="+this.getCpu_id()+" period="+this.getPeriod()+" deadline="+this.getDeadline()+" start="+this.getStart()+"\n";
        ret += "[ ";
        for(t_event ev : this.events) ret += ev.description()+", ";
        ret += "]";
        return ret;
    }

    // checking if the events are correctly imported (used in task_service)
    public void check_events() {
        int job = 0, preep = 0;
        int job_e = 0, preep_e = 0;
        boolean started = false;
        Iterator<t_event> it = this.getEvents().iterator();
        t_event evt = it.next();
        while(it.hasNext()) {
            evt = it.next();

            if(this.getEvents().get(this.getEvents().indexOf(evt)-1).getType().equals(evt.getType())) System.err.println(" bad time event "+this.getEvents().indexOf(evt)+" for next task");

            if(evt.getType().equals(Types.START)) { started = true; job++; }
            else if(evt.getType().equals(Types.FINISH)) { started = false; job_e++; }
            else if(evt.getType().equals(Types.SWITCH_IN) && started) preep++;
            else if(evt.getType().equals(Types.SWITCH_OUT) && started) preep_e++;
        }
        System.err.println("  tid: "+this.getId()+" idx: "+this.getName()+" cpu="+this.getCpu_id()+" period="+this.getPeriod()+" deadline="+this.getDeadline()+" start="+this.getStart()+"\n  jobs:"+job+"/"+job_e+" preemption:"+preep+"/"+preep_e);
        it = null;
    }

    public String getInfo() {
        int preem = 0, job = 0;
        for(t_event ev : this.events) { if(ev.isPassed()!=null && !ev.isPassed()) preem++; if(ev.getType().equals(Types.FINISH)) job++; }
        return "  task"+this.getName()+" TID:"+this.getId()+", cpu id:"+this.getCpu_id()+", period:"+this.getPeriod()/1000.0+"ms, deadline:"+this.getDeadline()/1000.0+"ms, start:"+this.getStart()+", jobs:"+job+", missed deadline:"+preem;
    }
}
