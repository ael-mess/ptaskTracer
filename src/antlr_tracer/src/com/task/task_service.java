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

    public void setTraces(List<trace> traces) {
        this.traces = traces;
        for(trace tr : this.traces) if((tr.getCpu_id()+1)>this.nb_cpu) this.nb_cpu = tr.getCpu_id()+1;
    }

    public Integer getNb_cpu() {
        return this.nb_cpu;
    }

    public void setTasks() {
        task t = new task();
        this.tasks = new ArrayList<>();
        for(trace tr : this.traces) if(tr instanceof ptask_tracepoint) {
            t.setId(tr.getTid());
            t.setCpu_id(tr.getCpu_id());
            if(this.tasks == null || !this.tasks.contains(t)) {
                this.tasks.add(t);
                t.setName(((ptask_tracepoint) tr).getIndex());
                t.setPeriod(((ptask_tracepoint) tr).getPeriod());
                t.setDeadline(((ptask_tracepoint) tr).getDeadline());
                t.setColor();
                this.setEvents(t);
            }
        }
        this.nb_task = this.tasks.size();
    }

    public Integer getNb_task() {
        return this.nb_task;
    }

    public Integer getNb_task_per_cpu(Integer cpu_id) {
        List<Integer> tids = new ArrayList<>();
        for(trace tr : this.traces) if(tr.getCpu_id()==cpu_id && !tids.contains(tr.getTid())) tids.add(tr.getTid());
        return tids.size();
    }

    public void check() {
        Integer nb = 0;
        for(int i=0; i<this.nb_cpu; i++) nb += this.getNb_task_per_cpu(i);
        if(nb != this.getNb_task()) { System.out.println(" Unknown trace  (nb of trace = "+this.getNb_task()+"!="+nb+")"); }

        nb = this.traces.get(0).getPid();
        for(trace tr : this.traces) if(nb != tr.getPid()) { System.out.println(" Unknown pid  (pid = "+tr.getPid()+"!="+nb+")"); }
    }

    private void setEvents(task t) {
        List<t_event> events = new ArrayList<>();
        for(trace tr : this.traces) {
            if(t.getId() == tr.getTid()) {
                if(tr instanceof clone_exit && (((clone_exit) tr).getRet() == 0)) t.setStart(tr.getTime().getTime("seconde"));
                else if(tr instanceof ptask_tracepoint) {
                    if(((ptask_tracepoint) tr).getFlag().equals("NOW")) {
                        t.setState(true);
                        if(((ptask_tracepoint) tr).getState().equals("b_wait_periode")) events.add(new t_event(Types.FINISH, tr.getTime().getTime("seconde")));
                        else if(((ptask_tracepoint) tr).getState().equals("e_wait_periode")) events.add(new t_event(Types.START, tr.getTime().getTime("seconde")));
                    }
                    else if(((ptask_tracepoint) tr).getFlag().equals("DEFERRED")) {
                        if(((ptask_tracepoint) tr).getState().equals("e_wait_activation") && !t.isActivated()) t.setState(true);
                        else if(((ptask_tracepoint) tr).getState().equals("b_wait_periode") && t.isActivated()) events.add(new t_event(Types.FINISH, tr.getTime().getTime("seconde")));
                        else if(((ptask_tracepoint) tr).getState().equals("e_wait_periode") && t.isActivated()) events.add(new t_event(Types.START, tr.getTime().getTime("seconde")));
                        else if(((ptask_tracepoint) tr).getState().equals("b_wait_activation") && t.isActivated()) events.add(new t_event(Types.FINISH, tr.getTime().getTime("seconde")));
                        else if(((ptask_tracepoint) tr).getState().equals("e_wait_activation") && t.isActivated()) events.add(new t_event(Types.START, tr.getTime().getTime("seconde")));
                    }
                }
                else if(tr instanceof sched_switch) events.add(new t_event(Types.SWITCH_OUT, tr.getTime().getTime("seconde"), ((sched_switch) tr).getNext_tid()));
            }
            else if(tr instanceof sched_switch && t.getId() == ((sched_switch) tr).getNext_tid()) events.add(new t_event(Types.SWITCH_IN, tr.getTime().getTime("seconde"), ((sched_switch) tr).getPrev_tid()));
        }
        t.setEvents(events);
    }

    public String description() {
        String ret = "";
        for(task ta : this.tasks) ret += ta.description()+"\n";
        return ret;
    }
}
