/****************************************************************************
* Class:       task_service()                                                *
* Parameters:  traces pointer, tasks pointer, and infos                      *
* Autor:       ael-mess                                                      *
* Description: truns the traces into tasks, main thread, and os apps         *
****************************************************************************/

package com.task;
import com.event.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.awt.Color;

import java.lang.NullPointerException;

public class task_service {
    protected List<task> tasks = null;
    protected List<trace> traces = null;
    protected List<Integer> cpu = null;
    protected List<Integer> tids = null;
    protected app main_task = null;
    protected List<app> os = null;
    protected Double end = 0.0;

    public task_service(List<trace> traces, String[] args) throws NullPointerException {
        try {
            this.traces = traces;
            this.tasks = new ArrayList<>();
            this.cpu = new ArrayList<>();
            this.tids = new ArrayList<>();
            for(trace tr : this.traces) {
                if(!cpu.contains(tr.getCpu_id())) this.cpu.add(tr.getCpu_id());
                if(tr instanceof ptask_tracepoint && !tids.contains(tr.getTid())) tids.add(tr.getTid());
            }
            String ret = "Actions performed in cpu: ";
            for(Integer id : this.cpu) ret += id+", ";
            System.out.println(ret);

            this.setMain_task();
            this.setOs_task();
            System.out.println("Main thread and system activity set");
            System.out.println("Tracing start from "+this.os.get(0).getStart()+" to "+this.end+" ("+(this.end-this.os.get(0).getStart())+"s)");

            List<Integer> tmp = new ArrayList<>(tids);
            for(trace tr : this.traces) if(tr instanceof ptask_tracepoint && tmp.remove(tr.getTid())) this.setTask(tr);
            tmp = null;
            System.out.println(""+this.tasks.size()+" tasks imported :");
            for(task t : this.tasks) System.out.println(t.getInfo());

            if(this.tasks.size()==0) throw new NullPointerException("No tasks detected");

            if(!this.Externset(args[0], Integer.valueOf(args[1]))) System.out.println("Two differents pid detected for "+args[0]+" :"+args[1]+" and "+this.main_task.getId());
        } catch(NullPointerException e) {
            if(this.tasks.size()==0) throw new NullPointerException("No tasks detected");
            throw new NullPointerException("Null pointer parameter");
        }
    }

    // for external informations
    public Boolean Externset(String name, Integer pid) throws NullPointerException {
        this.main_task.setName(name);
        return this.main_task.getId().equals(pid);
    }

    // setting the os activity per cpu in this list
    private void setOs_task() throws NullPointerException {
        this.os = new ArrayList<>();
        app proc;

        for(Integer id : this.cpu) {
            proc = new app();
            proc.setStart(this.traces.get(0).getTime().getTime("seconde"));
            proc.setId(id);
            proc.setCpu_id(id);
            proc.setColor();
            this.setOs_events(proc, id);
            os.add(proc);
        }
        this.end = this.traces.get(this.traces.size()-1).getTime().getTime("seconde");
    }

    // setting the main thread in this list
    private void setMain_task() throws NullPointerException {
        this.main_task = new app();
        Iterator<trace> it = this.traces.iterator();
        trace tr = it.next();
		while(it.hasNext() && !(tr instanceof ptask_tracepoint)) tr = it.next();
        this.main_task.setId(tr.getPid());
        this.tids.add(this.main_task.getId());
        this.main_task.setCpu_id(tr.getCpu_id());

        this.main_task.setColor();
        this.setApp_events();
        it = null;
    }

    // setting the tasks threads in this list
    private void setTask(trace tr) throws NullPointerException {
        task t = new task();
        this.tasks.add(t);
        t.setId(tr.getTid());
        t.setCpu_id(tr.getCpu_id());
        t.setName(((ptask_tracepoint) tr).getIndex());
        t.setPeriod(((ptask_tracepoint) tr).getPeriod());
        t.setDeadline(((ptask_tracepoint) tr).getDeadline());
        t.setColor();
        this.setEvents(t);
    }

    public List<Integer> getCpu() {
        return this.cpu;
    }

    public List<Integer> getTids() {
        return this.tids;
    }

    public List<task> getTasks() {
        return this.tasks;
    }

    public app getMain_task() {
        return this.main_task;
    }

    public List<app> getOs_task() {
        return this.os;
    }

    public Double getEnd() {
        return this.end;
    }

    // checking events errors in events importation (used only in debug)
    public void check_events() {
        t_event evt;
        for(task t : this.tasks) t.check_events();
        for(app proc : this.os) proc.check_events();
        this.main_task.check_events();
    }

    // setting task events
    private void setEvents(task t) throws NullPointerException {
        List<t_event> events = new ArrayList<>();
        int i = 0, idx = 0;
        boolean once = true;
        Double period = 0.0;
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
                        if(once) period = 0.0;
                        once = false;
                    }
                    i++;

                    if(t.isActivated()) {
                        if(((ptask_tracepoint) tr).getState().equals("\"b_wait_period\"")) {
                            if(t.getStart() != null) events.add(new t_event(Types.FINISH, tr.getTime().getTime("seconde"), (tr.getTime().getTime("seconde")-t.getStart())<((period+t.getDeadline())/1000000.0)));
                            else events.add(new t_event(Types.FINISH, tr.getTime().getTime("seconde")));
                        }
                        else if(((ptask_tracepoint) tr).getState().equals("\"e_wait_period\"")) {
                            events.add(new t_event(Types.START, tr.getTime().getTime("seconde")));
                            period += t.getPeriod();
                        }
                        else if(((ptask_tracepoint) tr).getState().equals("\"b_wait_activation\"")) {
                            if(t.getStart() != null) events.add(new t_event(Types.FINISH, tr.getTime().getTime("seconde"), (tr.getTime().getTime("seconde")-t.getStart())<((period+t.getDeadline())/1000000.0)));
                            else events.add(new t_event(Types.FINISH, tr.getTime().getTime("seconde")));
                        }
                        else if(((ptask_tracepoint) tr).getState().equals("\"e_wait_activation\"")) {
                            events.add(new t_event(Types.START, tr.getTime().getTime("seconde")));
                            period += t.getPeriod();
                        }
                    }
                    else if(((ptask_tracepoint) tr).getState().equals("\"e_wait_activation\"")) {
                        t.setState(true);
                        events.add(new t_event(Types.START, tr.getTime().getTime("seconde")));
                        period = 0.0;
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

    // setting the os events
    private void setOs_events(app proc, Integer id) throws NullPointerException {
        List<t_event> events = new ArrayList<>();

        for(trace tr : this.traces) if(tr instanceof sched_switch && tr.getCpu_id().equals(id)) {
            if(this.tids.contains(((sched_switch) tr).getNext_tid()) && !this.tids.contains(((sched_switch) tr).getPrev_tid()))
                events.add(new t_event(Types.SWITCH_OUT, tr.getTime().getTime("seconde"), ((sched_switch) tr).getPrev_tid(), ((sched_switch) tr).getPrev_comm()));
            else if(this.tids.contains(((sched_switch) tr).getPrev_tid()) && !this.tids.contains(((sched_switch) tr).getNext_tid()))
                events.add(new t_event(Types.SWITCH_IN, tr.getTime().getTime("seconde"), ((sched_switch) tr).getNext_tid(), ((sched_switch) tr).getNext_comm()));
        }
        proc.setEvents(events);
    }

    // setting the main app events
    private void setApp_events() throws NullPointerException {
        List<t_event> events = new ArrayList<>();
        boolean once = true;
        for(trace tr : this.traces) {
            if(tr instanceof clone_exit && ((clone_exit) tr).getRet().equals(this.main_task.getId()) && once) {
                this.main_task.setStart(tr.getTime().getTime("seconde"));
                once = false;
            }
            else if(tr instanceof sched_switch) {
                if(this.main_task.getId().equals(((sched_switch) tr).getNext_tid()))
                    events.add(new t_event(Types.SWITCH_IN, tr.getTime().getTime("seconde"), ((sched_switch) tr).getPrev_tid(), ((sched_switch) tr).getPrev_comm()));
                else if(this.main_task.getId().equals(((sched_switch) tr).getPrev_tid()) && this.main_task.getId().equals(tr.getPid()))
                    events.add(new t_event(Types.SWITCH_OUT, tr.getTime().getTime("seconde"), ((sched_switch) tr).getNext_tid(), ((sched_switch) tr).getNext_comm()));
            }
        }
        this.main_task.setEvents(events);
    }

    public String description() {
        String ret = "";
        for(task ta : this.tasks) ret += ta.description()+"\n";
        for(app proc : this.os) ret += proc.description()+"\n";
        ret += this.main_task.description()+"\n";
        return ret;
    }
}
