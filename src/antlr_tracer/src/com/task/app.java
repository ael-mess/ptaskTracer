/****************************************************************************
* Class:       app()                                                         *
* Parameters:  all the main() thread parameters (and os)                     *
* Autor:       ael-mess                                                      *
* Description: represents the parameters required for the main thread        *
****************************************************************************/

package com.task;

import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class app {
    protected Integer id = null;
    protected String name = null;
    protected Color color = null;
    protected Double start = null;
    protected Integer cpu_id = null;
    protected List<t_event> events = null;

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Color getColor() {
        return this.color;
    }

    public Double getStart() {
        return this.start;
    }

    public List<t_event> getEvents() {
        return this.events;
    }

    public Integer getCpu_id() {
        return this.cpu_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor() {
        Random randomGenerator = new Random();
        int r = randomGenerator.nextInt(256);
        int g = randomGenerator.nextInt(256);
        int b = randomGenerator.nextInt(256);
        this.color = (new Color(r,g,b)).darker();
    }

    public void setStart(Double start) {
        this.start = start;
    }

    public void setEvents(List<t_event> events) {
        this.events = events;
    }

    public void setCpu_id(Integer id) {
        this.cpu_id = id;
    }

    public String description() {
        String ret = "id: "+this.getId()+" name: "+this.getName()+" ("+this.getColor().toString()+")  start="+this.getStart()+"\n";
        ret += "[ ";
        for(t_event ev : this.events) ret += ev.description()+", ";
        ret += "]";
        return ret;
    }

    // checking if the events are correctly imported (used in task_service)
    public void check_events() {
        int job = 0;
        int preep = 0;
        int preep_e = 0;
        String comm = "";
        Iterator<t_event> it = this.getEvents().iterator();
        t_event evt = it.next();
        while(it.hasNext()) {
            evt = it.next();

            if(this.getEvents().get(this.getEvents().indexOf(evt)-1).getType().equals(evt.getType())) {System.err.println(" bad time event "+this.getEvents().indexOf(evt)+" for next task"); System.err.println(" evt-1"+this.getEvents().get(this.getEvents().indexOf(evt)-1).getType()+" evt"+this.getEvents().get(this.getEvents().indexOf(evt)).getType());}

            if(evt.getType().equals(Types.SWITCH_IN)) preep++;
            else if(evt.getType().equals(Types.SWITCH_OUT)) preep_e++;
        }
        System.err.println("  pid: "+this.getId()+" idx: "+this.getName()+" cpu="+this.getCpu_id()+" start="+this.getStart()+" preemption="+preep_e+"/"+preep);
        it = null;
    }
}
