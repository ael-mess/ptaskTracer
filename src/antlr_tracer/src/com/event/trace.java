/****************************************************************************
* Class:       trace()                                                       *
* Parameters:  all the trace parameters                                      *
* Autor:       ael-mess                                                      *
* Description: represents the parameters required for tracing                *
****************************************************************************/

package com.event;

public abstract class trace {
    protected Integer cpu_id = null;
    protected Integer pid = null;
    protected Integer tid = null;
    protected time_trace time = null;

    public Integer getCpu_id() {
        return this.cpu_id;
    }

    public Integer getPid() {
        return this.pid;
    }

    public Integer getTid() {
        return this.tid;
    }

    public time_trace getTime() {
        return this.time;
    }

    public abstract String description();
}
