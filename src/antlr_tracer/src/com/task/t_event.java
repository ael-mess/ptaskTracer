package com.task;

enum Types { START, FINISH, SWITCH_IN, SWITCH_OUT }

public class t_event {
    protected Double value = null;
    protected Types type = null;
    protected Integer np_tid = null;

    public t_event(Types type, Double value) {
        this.type = type;
        this.value = value;
    }

    public t_event(Types type, Double value, Integer tid) {
        this.type = type;
        this.value = value;
        this.np_tid = tid;
    }

    public Double getValue() {
        return this.value;
    }

    public Types getType() {
        return this.type;
    }

    public Integer getNp_tid() {
        return this.np_tid;
    }

    public String description() {
        return ""+this.getType().toString()+":"+this.getValue()+":"+this.getNp_tid();
    }
}
