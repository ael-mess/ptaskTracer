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
    protected Integer nb_task = 0;
    protected app main_task = null;
    protected List<app> os = null;
    protected Double end = 0.0;

    public task_service(List<trace> traces) throws NullPointerException {
        try {
            this.traces = traces;
            this.tasks = new ArrayList<>();
            this.cpu = new ArrayList<>();
            List<Integer> tids = new ArrayList<>();
            for(trace tr : this.traces) {
                if(!cpu.contains(tr.getCpu_id())) this.cpu.add(tr.getCpu_id());
                if(tr instanceof ptask_tracepoint && !tids.contains(tr.getTid())) tids.add(tr.getTid());
            }
            this.setMain_task();
            this.setOs_task();
            for(trace tr : this.traces) if(tr instanceof ptask_tracepoint && tids.remove(tr.getTid())) this.setTask(tr);
            this.nb_task = this.tasks.size();
            tids = null;
        } catch(NullPointerException e) {
            throw new NullPointerException("Null pointer parameter");
        }
    }

    // for external informations
    public void Externset(String name, Integer pid) {
        this.main_task.setName(name);
        this.main_task.setId(pid);
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
        t = null;
    }

    public List<Integer> getCpu() {
        return this.cpu;
    }

    public Integer getNb_task() {
        return this.nb_task;
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

    // checking errors in events importation (used only in debug)
    public void check() {
        if(this.traces!=null) System.err.println(" "+this.traces.size()+" traces saved");
        else System.err.println(" null traces pointer");

        if(this.main_task==null) System.err.println(" null main task pointer");
        if(this.os==null) System.err.println(" null os list pointer");
        System.err.println(" tracing start from "+this.os.get(0).getStart()+" to "+this.end+" ("+(this.end-this.os.get(0).getStart())+"s)");

        List<Integer> ptask_tids = new ArrayList<>();
        List<Integer> all_tids = new ArrayList<>();
        int main_pid = 0;
        for(trace tr : this.traces) {
            if(tr instanceof ptask_tracepoint && !ptask_tids.contains(tr.getTid())) {
                ptask_tids.add(tr.getTid());
                main_pid = tr.getPid();
            }
            if(!all_tids.contains(tr.getTid())) all_tids.add(tr.getTid());
        }
        if(!this.main_task.getId().equals(main_pid)) System.err.println(" two differents pid for main "+main_pid+" and "+this.main_task.getId());

        String ret = "\n  ";
        for(Integer id : all_tids) ret += id+", ";
        System.err.println(" tids in the traces :"+ret);

        if(this.tasks.size() != ptask_tids.size()) System.err.println(" missing task in the trace");
        if(this.tasks!=null) System.err.println(""+this.tasks.size()+" tasks detected :");
        else System.err.println(" null tasks pointer");
        ret = "\n  ";

        t_event evt;
        for(task t : this.tasks) {
            if(!ptask_tids.contains(t.getId())) System.err.println(" unknown task detected :"+t.getName()+":"+t.getId());
            t.check_events();
        }
        for(app proc : this.os) proc.check_events();
        this.main_task.check_events();

        ptask_tids = null;
        all_tids  = null;
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
                        if(once) period = t.getPeriod()*1.0;
                        once = false;
                    }
                    i++;

                    if(t.isActivated()) {
                        if(((ptask_tracepoint) tr).getState().equals("\"b_wait_period\"")) events.add(new t_event(Types.FINISH, tr.getTime().getTime("seconde"), (tr.getTime().getTime("seconde")-t.getStart())<(period/1000000.0)));
                        else if(((ptask_tracepoint) tr).getState().equals("\"e_wait_period\"")) {
                            events.add(new t_event(Types.START, tr.getTime().getTime("seconde")));
                            period += t.getPeriod();
                        }
                        else if(((ptask_tracepoint) tr).getState().equals("\"b_wait_activation\"")) events.add(new t_event(Types.FINISH, tr.getTime().getTime("seconde"), (tr.getTime().getTime("seconde")-t.getStart())<(period/1000000.0)));
                        else if(((ptask_tracepoint) tr).getState().equals("\"e_wait_activation\"")) {
                            events.add(new t_event(Types.START, tr.getTime().getTime("seconde")));
                            period += t.getPeriod();
                        }
                    }
                    else if(((ptask_tracepoint) tr).getState().equals("\"e_wait_activation\"")) {
                        t.setState(true);
                        events.add(new t_event(Types.START, tr.getTime().getTime("seconde")));
                        period = t.getPeriod()*1.0;
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
        List<Integer> tids = new ArrayList<>();
        for(trace tr : this.traces)
            if(tr instanceof ptask_tracepoint && !tids.contains(tr.getTid())) tids.add(tr.getTid());
        tids.add(this.main_task.getId());

        for(trace tr : this.traces) if(tr instanceof sched_switch && tr.getCpu_id().equals(id)) {
            if(tids.contains(((sched_switch) tr).getNext_tid()) && !tids.contains(((sched_switch) tr).getPrev_tid()))
                events.add(new t_event(Types.SWITCH_OUT, tr.getTime().getTime("seconde"), ((sched_switch) tr).getPrev_tid(), ((sched_switch) tr).getPrev_comm()));
            else if(tids.contains(((sched_switch) tr).getPrev_tid()) && !tids.contains(((sched_switch) tr).getNext_tid()))
                events.add(new t_event(Types.SWITCH_IN, tr.getTime().getTime("seconde"), ((sched_switch) tr).getNext_tid(), ((sched_switch) tr).getNext_comm()));
        }
        proc.setEvents(events);
        tids = null;
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
