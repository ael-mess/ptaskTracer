/****************************************************************************
* Class:       trace()                                                       *
* Parameters:  all the trace parameters                                      *
* Autor:       ael-mess                                                      *
* Description: represents the parameters required for tracing                *
****************************************************************************/

package com.event;

public class clone_exit extends kernel_syscall {
    protected Integer ret = null;

    public clone_exit(time_trace time, Integer cpu_id, Integer pid, Integer tid, Integer ret) {
        this.time = time;
        this.cpu_id = cpu_id;
        this.pid = pid;
        this.tid = tid;
        this.ret = ret;
    }

    public Integer getRet() {
        return this.ret;
    }

    public String description() {
        return "["+super.time.getHour()+":"+super.time.getMinute()+":"+super.time.getSeconde()+"] (+"+super.time.getDelta()+") cpu id="+super.getCpu_id()+", pid="+super.getPid()+", tid="+super.getTid()+"  "+this.getRet();
    }
}
