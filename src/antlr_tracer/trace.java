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

    public Double getTime(String in) {
        return this.time.getTime(in);
    }
}
