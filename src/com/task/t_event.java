/****************************************************************************
* Class:       t_event()                                                     *
* Parameters:  the time task parameters                                      *
* Autor:       ael-mess                                                      *
* Description: represents the parameters required for timing                 *
****************************************************************************/

package com.task;

public class t_event {
    protected Double value = null;
    protected Types type = null;
    protected Integer np_tid = null;
    protected String np_name = null;
    protected Boolean pass = null;

    //differents construcors for all Types of t_events

    public t_event(Types type, Double value) {
        this.type = type;
        this.value = value;
    }

    public t_event(Types type, Double value, Boolean pass) {
        this.type = type;
        this.value = value;
        this.pass = pass;
    }

    public t_event(Types type, Double value, Integer tid) {
        this.type = type;
        this.value = value;
        this.np_tid = tid;
    }

    public t_event(Types type, Double value, Integer tid, String name) {
        this.type = type;
        this.value = value;
        this.np_tid = tid;
        this.np_name = name;
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

    public String getNp_name() {
        return this.np_name;
    }

    public Boolean isPassed() {
        return this.pass;
    }

    public String description() {
        return ""+this.getType().toString()+":"+this.getValue()+":"+this.getNp_tid()+":"+this.getNp_name()+":"+this.isPassed();
    }
}
