package com.event;

public class ptask_tracepoint extends userspace_tracepoint {
    protected String flag = null;
    protected String state = null;
    protected Integer index = null;
    protected Integer times = null;
    protected Integer prio = null;
    protected Integer period = null;
    protected Integer deadline = null;

    public ptask_tracepoint(time_trace time, Integer cpu_id, String pid, String tid, String flag, String state, String idx, String tim, String prio, String peri, String dd) {
        this.time = time;
        this.cpu_id = cpu_id;
        this.pid = Integer.valueOf(pid);
        this.tid = Integer.valueOf(tid);
        this.flag = flag;
        this.state = state;
        this.index = Integer.valueOf(idx);
        this.times = Integer.valueOf(tim);
        this.prio = Integer.valueOf(prio);
        this.period = Integer.valueOf(peri);
        this.deadline = Integer.valueOf(dd);
    }

    public String getFlag() {
        return this.flag;
    }

    public String getState() {
        return this.state;
    }

    public Integer getIndex() {
        return this.index;
    }

    public Integer getTimes() {
        return this.times;
    }

    public Integer getPrio() {
        return this.prio;
    }

    public Integer getPeriod() {
        return this.period;
    }

    public Integer getDeadline() {
        return this.deadline;
    }

    public String description() {
        return "["+super.time.getHour()+":"+super.time.getMinute()+":"+super.time.getSeconde()+"] (+"+super.time.getDelta()+") cpu id="+super.getCpu_id()+", pid="+super.getPid()+", tid="+super.getTid()+"  "+this.getFlag()+"  "+this.getState()+"  "+this.getIndex()+"  "+this.getTimes()+"  "+this.getPrio()+"  "+this.getPeriod()+"  "+this.getDeadline();
    }
}
