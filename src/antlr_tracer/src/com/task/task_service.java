package com.task;
import com.event.*;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class task_service {
    protected List<task> tasks = null;
    protected List<trace> traces = null;
    protected Integer nb_cpu = 0;
    protected Integer nb_task = 0;
    protected Double start = 0.0;
    protected Double end = 0.0;

    public task_service(List<trace> traces) {
        this.traces = traces;
        this.tasks = new ArrayList<>();
        List<Integer> tids = new ArrayList<>();
        for(trace tr : this.traces) {
            if((tr.getCpu_id()+1)>this.nb_cpu) this.nb_cpu = tr.getCpu_id()+1;
            if(tr instanceof ptask_tracepoint && !tids.contains(tr.getTid())) tids.add(tr.getTid());
        }
        for(trace tr : this.traces) if(tr instanceof ptask_tracepoint && tids.remove(tr.getTid())) this.setTask(tr);
        this.nb_task = this.tasks.size();
        this.start = this.traces.get(0).getTime().getTime("seconde");
        this.end = this.traces.get(traces.size()-1).getTime().getTime("seconde");
        tids = null;
    }

    private void setMainTask(trace tr) {
        task t = new task();
        this.tasks.add(t);
        t.setId(tr.getTid());
        t.setCpu_id(tr.getCpu_id());
        t.setName(((ptask_tracepoint) tr).getIndex());
        t.setPeriod(((ptask_tracepoint) tr).getPeriod());
        t.setDeadline(((ptask_tracepoint) tr).getDeadline());
        t.setColor();
        this.setEvents(t);
        t = null;
    }

    private void setTask(trace tr) {
        task t = new task();
        this.tasks.add(t);
        t.setId(tr.getTid());
        t.setCpu_id(tr.getCpu_id());
        t.setName(((ptask_tracepoint) tr).getIndex());
        t.setPeriod(((ptask_tracepoint) tr).getPeriod());
        t.setDeadline(((ptask_tracepoint) tr).getDeadline());
        t.setColor();
        this.setEvents(t);
        t = null;
    }

    public Integer getNb_cpu() {
        return this.nb_cpu;
    }

    public Integer getNb_task() {
        return this.nb_task;
    }

    public Double getStart() {
        return this.start;
    }

    public Double getEnd() {
        return this.end;
    }

    public List<task> getTasks() {
        return this.tasks;
    }

    public Integer getNb_task_per_cpu(Integer cpu_id) {
        List<Integer> tids = new ArrayList<>();
        for(trace tr : this.traces) if(tr.getCpu_id().equals(cpu_id) && !tids.contains(tr.getTid())) tids.add(tr.getTid());
        return tids.size();
    }

    public void check() {
        int nb = 0;
        for(int i=0; i<this.nb_cpu; i++) nb += this.getNb_task_per_cpu(i);
        if(nb != this.getNb_task()) { System.err.println(" Unknown trace  (nb of trace = "+this.getNb_task()+"!="+nb+")"); }

        nb = this.traces.get(0).getPid();
        for(trace tr : this.traces) if(!tr.getPid().equals(nb)) { System.err.println(" Unknown pid  (pid = "+tr.getPid()+"!="+nb+")"); }

        boolean started = false;
        for(task t : this.tasks) {
            nb = 0;
            for(t_event t_e : t.getEvents()) {
                if(t_e.getType().equals(Types.START)) {
                    started = true;
                    nb ++;
                }
                else if(t_e.getType().equals(Types.FINISH)) started = false;
                else if(t_e.getType().equals(Types.SWITCH_OUT) && started) nb++;
            }
            System.err.println("   Task:"+t.getName()+"  (nb of actions = "+nb+")");
        }
    }

    private void setEvents(task t) {
        List<t_event> events = new ArrayList<>();
        int i = 0, idx = 0;
        boolean once = true;
        for(trace tr : this.traces) {
            i = 0;
            if(t.getId().equals(tr.getTid())) {
                if(tr instanceof clone_exit && (((clone_exit) tr).getRet().equals(0))) {
                    t.setStart(tr.getTime().getTime("seconde"));
                    idx = i;
                }
                else if(tr instanceof ptask_tracepoint) {
                    if(((ptask_tracepoint) tr).getFlag().equals("\"NOW\"")) {
                        t.setState(true);
                        if(once && t.getStart()!=null) events.add(idx, new t_event(Types.START, t.getStart()));
                        once = false;
                    }
                    i++;

                    if(t.isActivated()) {
                        if(((ptask_tracepoint) tr).getState().equals("\"b_wait_period\"")) events.add(new t_event(Types.FINISH, tr.getTime().getTime("seconde")));
                        else if(((ptask_tracepoint) tr).getState().equals("\"e_wait_period\"")) events.add(new t_event(Types.START, tr.getTime().getTime("seconde")));
                        else if(((ptask_tracepoint) tr).getState().equals("\"b_wait_activation\"")) events.add(new t_event(Types.FINISH, tr.getTime().getTime("seconde")));
                        else if(((ptask_tracepoint) tr).getState().equals("\"e_wait_activation\"")) events.add(new t_event(Types.START, tr.getTime().getTime("seconde")));
                    }
                    else if(((ptask_tracepoint) tr).getState().equals("\"e_wait_activation\"")) {
                        t.setState(true);
                        events.add(new t_event(Types.START, tr.getTime().getTime("seconde")));
                    }
                }
                else if(tr instanceof sched_switch) {
                    events.add(new t_event(Types.SWITCH_OUT, tr.getTime().getTime("seconde"), ((sched_switch) tr).getNext_tid()));
                    i++;
                }
            }
            else if(tr instanceof sched_switch && t.getId().equals(((sched_switch) tr).getNext_tid())) {
                events.add(new t_event(Types.SWITCH_IN, tr.getTime().getTime("seconde"), ((sched_switch) tr).getPrev_tid()));
                i++;
            }
        }
        t.setEvents(events);
    }

    public String description() {
        String ret = "";
        for(task ta : this.tasks) ret += ta.description()+"\n";
        return ret;
    }
}
