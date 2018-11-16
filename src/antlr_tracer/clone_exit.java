public class clone_exit extends kernel_syscall {
    protected Integer ret = null;

    public clone_exit(Integer cpu_id, Integer pid, Integer tid, Integer ret) {
        this.cpu_id = cpu_id;
        this.pid = pid;
        this.tid = tid;
        this.ret = ret;
    }

    public Integer getRet() {
        return this.ret;
    }
}
