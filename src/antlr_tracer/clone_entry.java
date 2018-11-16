public class clone_entry extends kernel_syscall {
    protected Long clone_flag = null;
    protected Long newsp = null;
    protected Long parent_tid = null;
    protected Long child_tid = null;

    public clone_entry(Integer cpu_id, Integer pid, Integer tid, time_trace time, String flag, String newsp, String ptid, String ctid) {
        this.cpu_id = cpu_id;
        this.pid = pid;
        this.tid = tid;
        this.clone_flag = Long.parseLong(flag, 16);
        this.newsp = Long.parseLong(newsp, 16);
        this.parent_tid = Long.parseLong(ptid, 16);
        this.child_tid = Long.parseLong(ctid, 16);
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
}
