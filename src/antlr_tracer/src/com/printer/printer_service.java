/****************************************************************************
* Class:       printer_service()                                             *
* Parameters:  task service class, svg generator                             *
* Autor:       ael-mess                                                      *
* Description: generates the svg file and draw the actions                   *
****************************************************************************/

package com.printer;
import com.task.*;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.Element;

public class printer_service {
    protected svggen svg = null;
    protected task_service tasks = null;
    protected Element root = null;

    public printer_service(task_service t, String out) throws IOException, NullPointerException {
        try {
            this.tasks = t;
            this.svg = new svggen(t, out);
            svg.setCanvas();

            this.root = this.svg.getDoc().getDocumentElement();
            this.svg.getGraph().getRoot(root);

            this.draw_os();
            this.draw_main();
            this.draw_tasks();
            svg.streamOut(root);
        } catch(NullPointerException e) {
            throw new NullPointerException("Null pointer parameter");
        }
    }

    // drawing tasks jobs calling tooltips (param: h) height 6/2 smaller
    public void draw_tasks() throws IOException {
        double h = this.svg.getOs().size()+ 1.0;
        double w = 0.0;
        double x = 0.0;
        double y = this.svg.getTask_hei();
        boolean started = false;
        boolean switched = false;
        List<String> text = new ArrayList<>();
        for(task t : this.svg.getTasks()) {
            text.add(0, "task"+t.getName()+" PID:"+t.getId()+" P:"+t.getPeriod()/1000.0+"ms D:"+t.getDeadline()/1000.0+"ms CPU:"+t.getCpu_id());
            for(t_event t_e : t.getEvents()) {
                switch(t_e.getType()) {
                    case START :
                        started = true;
                        x = (t_e.getValue() - this.svg.getStart())*this.svg.getScale();
                        text.add(1, "from "+((t_e.getValue()-this.svg.getStart())*1000.0)+"ms");
                        break;
                    case FINISH :
                        if(started && !switched) {
                            started = false;
                            w = (t_e.getValue() - this.svg.getStart())*this.svg.getScale() - x;
                            text.add(1, text.get(1)+" to "+((t_e.getValue()-this.svg.getStart())*1000.0)+"ms");
                            if(t_e.isPassed()) this.svg.getForm().jobNS(root, this.svg.getDoc(), x, h*y+3.0, w, y-6.0, "green", text);
                            else if(!t_e.isPassed()) this.svg.getForm().jobNS(root, this.svg.getDoc(), x, h*y-3.0, w, y-6.0, "red", text);
                        }
                        break;
                    case SWITCH_OUT :
                        if(started && !switched) {
                            switched = true;
                            w = (t_e.getValue() - this.svg.getStart())*this.svg.getScale() - x;
                            text.add(1, text.get(1)+" to "+((t_e.getValue()-this.svg.getStart())*1000.0)+"ms");
                            this.svg.getForm().jobNS(root, this.svg.getDoc(), x, h*y+3.0, w, y-6.0, "green", text);
                        }
                        break;
                    case SWITCH_IN :
                        if(started && switched) {
                            switched = false;
                            x = (t_e.getValue() - this.svg.getStart())*this.svg.getScale();
                            text.add(1, "from "+((t_e.getValue()-this.svg.getStart())*1000.0)+"ms");
                        }
                        break;
                }
            }
            h += 1.0;
        }
        text = null;
    }

    // drawing main thread (param: h)
    public void draw_main() throws IOException {
        double w = 0.0;
        double x = 0.0;
        double h = this.svg.getOs().size();
        double y = this.svg.getTask_hei();
        boolean started = false;
        List<String> text = new ArrayList<>();
        for(t_event t_e : this.svg.getApp().getEvents()) {
            text.add(this.svg.getApp().getName()+" PID:"+this.svg.getApp().getId()+" CPU:"+this.svg.getApp().getCpu_id());
            switch(t_e.getType()) {
                case SWITCH_OUT :
                    if(started) {
                        started = false;
                        w = (t_e.getValue() - this.svg.getStart())*this.svg.getScale() - x;
                        text.add(1, text.get(1)+" to "+((t_e.getValue()-this.svg.getStart())*1000.0)+"ms");
                        this.svg.getForm().jobNS(root, this.svg.getDoc(), x, h*y, w, y, "blue", text);
                    }
                    break;
                case SWITCH_IN :
                    if(!started) {
                        started = true;
                        x = (t_e.getValue() - this.svg.getStart())*this.svg.getScale();
                        text.add(1, "from "+((t_e.getValue()-this.svg.getStart())*1000.0)+"ms");
                    }
                    break;
            }
        }
        text = null;
    }

    // drawing os activity (param: h)
    public void draw_os() throws IOException {
        double h = 0.0;
        double w = 0.0;
        double x = 0.0;
        double y = this.svg.getTask_hei();
        boolean started = false;
        List<String> text = new ArrayList<>();
        for(app proc : this.svg.getOs()) {
            for(t_event t_e : proc.getEvents()) {
                switch(t_e.getType()) {
                    case SWITCH_OUT :
                        if(started) {
                            text.add(1, "last: "+t_e.getNp_name()+" TID:"+t_e.getNp_tid());
                            started = false;
                            w = (t_e.getValue() - this.svg.getStart())*this.svg.getScale() - x;
                            this.svg.getForm().jobNS(root, this.svg.getDoc(), x, h*y, w, y, "blue", text);
                        }
                        break;
                    case SWITCH_IN :
                        if(!started) {
                            text.add(0, "first: "+t_e.getNp_name()+" TID:"+t_e.getNp_tid());
                            started = true;
                            x = (t_e.getValue() - this.svg.getStart())*this.svg.getScale();
                        }
                        break;
                }
            }
        }
        text = null;
    }
}
