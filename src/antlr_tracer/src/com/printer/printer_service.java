/****************************************************************************
* Class:       printer_service()                                             *
* Parameters:  task service class, svg generator                             *
* Autor:       ael-mess                                                      *
* Description: generates the svg file and draw the actions                   *
****************************************************************************/

package com.printer;
import com.task.*;

import java.io.IOException;
import java.lang.OutOfMemoryError;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.Element;

public class printer_service {
    protected svggen svg = null;
    protected task_service tasks = null;
    protected Element root = null;
    protected Integer curH = 0;

    public printer_service(task_service t, String out, String[] args) throws IOException, NullPointerException, OutOfMemoryError {
        try {
            this.tasks = t;
            this.svg = new svggen(t, out);
            System.out.println("SVG file generated in build/");

            this.Externset(Double.valueOf(args[2]), Double.valueOf(args[3]), Boolean.valueOf(args[4]), Boolean.valueOf(args[5]));
            this.svg.setCanvas();
            System.out.println("Canvas set");

            this.root = this.svg.getDoc().getDocumentElement();
            this.svg.getGraph().getRoot(root);

            if(this.svg.isOsactive()) this.draw_os();
            else this.curH++;
            this.draw_main();
            this.draw_tasks();
            System.out.println("Threads printed in build/"+this.svg.getApp().getName()+".svg");
            svg.streamOut(root);
            System.out.println("SVG file savedin build/"+this.svg.getApp().getName()+".svg");
        } catch(NullPointerException e) {
            throw new NullPointerException("Null pointer parameter");
        } catch (OutOfMemoryError e) {
            System.err.println("Exception in thread \"main\" java.lang.OutOfMemoryError: Java heap space: failed reallocation (too many events in the trace file)\n        at com.printer.printer_service.<init>(printer_service.java:46)\n        at com.main.main(main.java:30)");
            System.exit(2);
        }
    }

    // for external informations
    public void Externset(Double scale, Double task_h, Boolean osactive, Boolean cpu) throws NullPointerException {
        this.svg.setScale(scale);
        this.svg.setTask_hei(task_h);
        this.svg.setOsactive(osactive);
        this.svg.setPercpu(cpu);
    }

    // drawing tasks jobs calling tooltips (param: h) height 6/2 smaller
    public void draw_tasks() throws NullPointerException {
        int h = this.curH;
        double w = 0.0;
        double x = 0.0;
        double y = this.svg.getTask_hei();
        boolean started = false;
        boolean switched = false;
        int os_preem = 0;
        String text1 = "", text2 = "";
        for(task t : this.svg.getTasks()) {
            if(this.svg.isPercpu()) h = this.curH + t.getCpu_id();
            for(t_event t_e : t.getEvents()) {
                switch(t_e.getType()) {
                    case START :
                        started = true;
                        x = (t_e.getValue() - this.svg.getStart())*this.svg.getScale();
                        text1 = "task"+t.getName()+" PID:"+t.getId()+" P:"+t.getPeriod()/1000.0+"ms D:"+t.getDeadline()/1000.0+"ms CPU:"+t.getCpu_id();
                        text2 = "from "+((t_e.getValue()-this.svg.getStart())*1000.0)+"ms";
                        os_preem = 0;
                        break;
                    case FINISH :
                        if(started && !switched) {
                            started = false;
                            w = (t_e.getValue() - this.svg.getStart())*this.svg.getScale() - x;
                            text1 += " ("+os_preem+" os preem)";
                            text2 += " to "+((t_e.getValue()-this.svg.getStart())*1000.0)+"ms";
                            this.svg.getForm().jobNS(root, this.svg.getDoc(), x, h*y+3.0, w, y-6.0, this.getColor(t_e, t), text1, text2);
                        }
                        break;
                    case SWITCH_OUT :
                        if(started && !switched) {
                            if(this.svg.isOsactive() || this.tasks.getTids().contains(t_e.getNp_tid())) {
                                switched = true;
                                w = (t_e.getValue() - this.svg.getStart())*this.svg.getScale() - x;
                                text1 += " ("+os_preem+" os preem)";
                                text2 += " to "+((t_e.getValue()-this.svg.getStart())*1000.0)+"ms";
                                this.svg.getForm().jobNS(root, this.svg.getDoc(), x, h*y+3.0, w, y-6.0, this.getColor(t_e, t), text1, text2);
                            }
                            else os_preem++;
                        }
                        break;
                    case SWITCH_IN :
                        if(started && switched) {
                            if(this.svg.isOsactive() || this.tasks.getTids().contains(t_e.getNp_tid())) {
                                switched = false;
                                x = (t_e.getValue() - this.svg.getStart())*this.svg.getScale();
                                text1 = "task"+t.getName()+" PID:"+t.getId()+" P:"+t.getPeriod()/1000.0+"ms D:"+t.getDeadline()/1000.0+"ms CPU:"+t.getCpu_id();
                                text2 = "from "+((t_e.getValue()-this.svg.getStart())*1000.0)+"ms";
                                os_preem = 0;
                            }
                        }
                        break;
                }
            }
            if(!this.svg.isPercpu()) h++;
        }
    }

    private String getColor(t_event evt, task t) {
        if(this.svg.isPercpu() || t.getStart()==null) return t.getColorS();
        else if(evt.isPassed()!=null && !evt.isPassed()) return "red";
        else return "green";
    }

    // drawing main thread (param: h)
    public void draw_main() throws NullPointerException {
        double w = 0.0;
        double x = 0.0;
        double y = this.svg.getTask_hei();
        boolean started = false;
        String text1 = "", text2 = "";
        for(t_event t_e : this.svg.getApp().getEvents()) {
            text1 = ""+this.svg.getApp().getName()+" PID:"+this.svg.getApp().getId()+" CPU:"+this.svg.getApp().getCpu_id();
            switch(t_e.getType()) {
                case SWITCH_OUT :
                    if(started) {
                        text2 += " to "+((t_e.getValue()-this.svg.getStart())*1000.0)+"ms";
                        started = false;
                        w = (t_e.getValue() - this.svg.getStart())*this.svg.getScale() - x;
                        this.svg.getForm().jobNS(root, this.svg.getDoc(), x, this.curH*y, w, y, "blue", text1, text2);
                    }
                    break;
                case SWITCH_IN :
                    if(!started) {
                        text2 = "from "+((t_e.getValue()-this.svg.getStart())*1000.0)+"ms";
                        started = true;
                        x = (t_e.getValue() - this.svg.getStart())*this.svg.getScale();
                    }
                    break;
            }
        }
        this.curH++;
    }

    // drawing os activity (param: h)
    public void draw_os() throws NullPointerException {
        double w = 0.0;
        double x = 0.0;
        double y = this.svg.getTask_hei();
        boolean started = false;
        String text1 = "", text2 = "";
        this.curH = 0;
        for(app proc : this.svg.getOs()) {
            for(t_event t_e : proc.getEvents()) {
                switch(t_e.getType()) {
                    case SWITCH_OUT :
                        if(started) {
                            text2 = "last: "+t_e.getNp_name()+" TID:"+t_e.getNp_tid();
                            started = false;
                            w = (t_e.getValue() - this.svg.getStart())*this.svg.getScale() - x;
                            this.svg.getForm().jobNS(root, this.svg.getDoc(), x, this.curH*y, w, y, "blue", text1, text2);
                        }
                        break;
                    case SWITCH_IN :
                        if(!started) {
                            text1 = "first: "+t_e.getNp_name()+" TID:"+t_e.getNp_tid();
                            started = true;
                            x = (t_e.getValue() - this.svg.getStart())*this.svg.getScale();
                        }
                        break;
                }
            }
            this.curH++;
        }
    }
}
