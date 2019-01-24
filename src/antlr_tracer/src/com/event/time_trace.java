package com.event;

public class time_trace {
    protected Double hour = null;
    protected Double minute = null;
    protected Double seconde = null;
    protected Double delta = null;

    public time_trace(Integer h, Integer m, Integer s, String os, String d) {
        this.hour = (double) h;
        this.minute = (double) m;
        this.seconde = (double) s + Double.valueOf("0."+os);
        this.delta = Double.valueOf(d);
    }

    public void setTime(Double h, Double m, Double s, Double d) {
        this.hour = h;
        this.minute = m;
        this.seconde = s;
        this.delta = d;
    }

    public Double getTime(String in) {
        if(in.equals("minute")) return (this.hour*60 + this.minute + this.seconde/60);
        else if(in.equals("hour")) return (this.hour + this.minute/60 + this.seconde/60);
        else if(in.equals("seconde")) return (this.hour*60 + this.minute*60 + this.seconde);
        else return -1.;
    }

    public Double getDelta() {
        return this.delta;
    }

    public Double getHour() {
        return this.hour;
    }

    public Double getMinute() {
        return this.minute;
    }

    public Double getSeconde() {
        return this.seconde;
    }
}
