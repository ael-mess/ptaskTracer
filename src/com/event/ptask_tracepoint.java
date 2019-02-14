/****************************************************************************
* Class:       trace()                                                       *
* Parameters:  all the trace parameters                                      *
* Autor:       ael-mess                                                      *
* Description: represents the parameters required for tracing                *
****************************************************************************/

package com.event;

import java.math.BigInteger;

public class ptask_tracepoint extends userspace_tracepoint {
    protected String flag = null;
    protected String state = null;
    protected Integer index = null;
    protected Long times = null;
    protected Integer prio = null;
    protected Integer period = null;
    protected Integer deadline = null;

    public ptask_tracepoint(time_trace time, Integer cpu_id, Integer pid, Integer tid, String flag, String state, Integer idx, String tim, Integer prio, Integer peri, Integer dd) {
        BigInteger bigInt = new BigInteger(tim);

        this.time = time;
        this.cpu_id = cpu_id;
        this.pid = pid;
        this.tid = tid;
        this.flag = flag;
        this.state = state;
        this.index = idx;
        if(bigInt.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0) this.times = Long.valueOf(tim);
        this.prio = prio;
        this.period = peri;
        this.deadline = dd;
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

    public Long getTimes() {
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
