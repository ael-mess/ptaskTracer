public class ptask_tracepoint extends userspace_tracepoint {
    protected String flag = null;
    protected String state = null;
    protected Integer index = null;
    protected Integer times = null;
    protected Integer prio = null;
    protected Integer periode = null;
    protected Integer deadline = null;

    public ptask_tracepoint(Integer cpu_id, Integer pid, Integer tid, String flag, String state, Integer idx, Integer tim, Integer prio, Integer peri, Integer dd) {
        this.cpu_id = cpu_id;
        this.pid = pid;
        this.tid = tid;
        this.flag = flag;
        this.state = state;
        this.index = idx;
        this.times = tim;
        this.prio = prio;
        this.periode = peri;
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

    public Integer getTimes() {
        return this.times;
    }

    public Integer getPrio() {
        return this.prio;
    }

    public Integer getPeriode() {
        return this.periode;
    }

    public Integer getDeadline() {
        return this.deadline;
    }
}