package com.event;

public class clone_entry extends kernel_syscall {
    protected Long clone_flag = null;
    protected Long newsp = null;
    protected Long parent_tid = null;
    protected Long child_tid = null;

    public clone_entry(time_trace time, Integer cpu_id, Integer pid, Integer tid, String flag, String newsp, String ptid, String ctid) {
        this.time = time;
        this.cpu_id = cpu_id;
        this.pid = pid;
        this.tid = tid;
        this.clone_flag = Long.parseLong(flag.substring(2), 16);
        this.newsp = Long.parseLong(newsp.substring(2), 16);
        this.parent_tid = Long.parseLong(ptid.substring(2), 16);
        this.child_tid = Long.parseLong(ctid.substring(2), 16);
    }

    public Long getClone_flag() {
        return this.clone_flag;
    }

    public Long getNewsp() {
        return this.newsp;
    }

    public Long getParent_tid() {
        return this.parent_tid;
    }

    public Long getChild_tid() {
        return this.child_tid;
    }

    public String description() {
        return "["+super.time.getHour()+":"+super.time.getMinute()+":"+super.time.getSeconde()+"] (+"+super.time.getDelta()+") cpu id="+super.getCpu_id()+", pid="+super.getPid()+", tid="+super.getTid()+"  "+this.getClone_flag()+"  "+this.getNewsp()+"  "+this.getChild_tid()+"  "+this.getParent_tid();
    }
}
