public class sched_switch extends kernel_tracepoint {
    protected String prev_comm = null;
    protected String next_comm = null;
    protected Integer prev_tid = null;
    protected Integer next_tid = null;
    protected Integer prev_prio = null;
    protected Integer next_prio = null;
    protected Integer prev_state = null;

    public sched_switch(Integer cpu_id, Integer pid, Integer tid, String pcomm, String ncomm, Integer ptid, Integer ntid, Integer pprio, Integer nprio, Integer ps) {
        this.cpu_id = cpu_id;
        this.pid = pid;
        this.tid = tid;
        this.prev_comm = pcomm;
        this.next_comm = ncomm;
        this.prev_tid = ptid;
        this.next_tid = ntid;
        this.prev_prio = pprio;
        this.next_prio = nprio;
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
}
