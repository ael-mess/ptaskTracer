/****************************************************************************
* Class:       trace()                                                       *
* Parameters:  all the trace parameters                                      *
* Autor:       ael-mess                                                      *
* Description: represents the parameters required for tracing                *
****************************************************************************/

package com.event;

public class sched_switch extends kernel_tracepoint {
    protected String prev_comm = null;
    protected String next_comm = null;
    protected Integer prev_tid = null;
    protected Integer next_tid = null;
    protected Integer prev_prio = null;
    protected Integer next_prio = null;
    protected Integer prev_state = null;

    public sched_switch(time_trace time, Integer cpu_id, Integer pid, Integer tid, String pcomm, String ncomm, Integer ptid, Integer ntid, String pprio, String nprio, Integer ps) {
        this.time = time;
        this.cpu_id = cpu_id;
        this.pid = pid;
        this.tid = tid;
        this.prev_comm = pcomm;
        this.next_comm = ncomm;
        this.prev_tid = ptid;
        this.next_tid = ntid;
        this.prev_prio = Integer.valueOf(pprio);
        this.next_prio = Integer.valueOf(nprio);
        this.prev_state = ps;
    }

    public String getPrev_comm() {
        return this.prev_comm;
    }

    public String getNext_comm() {
        return this.next_comm;
    }

    public Integer getPrev_tid() {
        return this.prev_tid;
    }

    public Integer getNext_tid() {
        return this.next_tid;
    }

    public Integer getPrev_prio() {
        return this.prev_prio;
    }

    public Integer getNext_prio() {
        return this.next_prio;
    }

    public Integer getPrev_state() {
        return this.prev_state;
    }

    public String description() {
        return "["+super.time.getHour()+":"+super.time.getMinute()+":"+super.time.getSeconde()+"] (+"+super.time.getDelta()+") cpu id="+super.getCpu_id()+ ", pid="+super.getPid()+", tid="+super.getTid()+"  "+this.getPrev_comm()+"  "+this.getNext_comm()+"  "+this.getPrev_tid()+"  "+this.getNext_tid()+"  "+this.getPrev_prio()+"  "+this.getNext_prio()+"  "+this.getPrev_state();
    }
}
