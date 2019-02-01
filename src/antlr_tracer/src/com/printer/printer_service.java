package com.printer;
import com.task.*;

import java.io.IOException;

public class printer_service {
    protected svggen svg = null;
    protected task_service tasks = null;

    public printer_service(task_service t) throws IOException {
        this.tasks = t;
        this.svg = new svggen(t);

        svg.setCanvas();
        this.draw_tasks();
        svg.streamOut();
    }

    public void draw_tasks() {
        double h = 0.0;
        double w = 0.0;
        double x = 0.0;
        double y = this.svg.getTask_hei();
        boolean started = false;
        boolean switched = false;
        int test = 0;
        for(task t : this.svg.getTasks()) {
            test = 0;
            for(t_event t_e : t.getEvents()) {
                switch(t_e.getType()) {
                    case START :
                        started = true;
                        x = (t_e.getValue() - this.svg.getStart())*this.svg.getScale();
                        break;
                    case FINISH :
                        if(started && !switched) {
                            started = false;
                            w = (t_e.getValue() - this.svg.getStart())*this.svg.getScale() - x;
                            this.svg.getForm().job(x, h*y, w, y, t.getColor());
                            System.err.println("Finish x:"+x+" y:"+h*y+" w:"+w+" h:"+y);
                            test++;
                        }
                        break;
                    case SWITCH_OUT :
                        if(started && !switched) {
                            switched = true;
                            w = (t_e.getValue() - this.svg.getStart())*this.svg.getScale() - x;
                            this.svg.getForm().job(x, h*y, w, y, t.getColor());
                            System.err.println("Switch out x:"+x+" y:"+h*y+" w:"+w+" h:"+y);
                            test++;
                        }
                        break;
                    case SWITCH_IN :
                        if(started && switched) {
                            switched = false;
                            x = (t_e.getValue() - this.svg.getStart())*this.svg.getScale();
                        }
                        break;
                }
            }
            h += 1.0;
            System.err.println("   Task:"+t.getName()+"  (nb of actions = "+test+")");
        }
    }
}
