package com.printer;
import com.task.*;

import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import java.awt.*;
import java.awt.geom.*;

import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.dom.GenericDOMImplementation;

import org.w3c.dom.Document;
import org.w3c.dom.DOMImplementation;

public class svggen {
    protected Document doc = null;
    protected SVGGeneratorContext ctx = null;
    protected SVGGraphics2D svgGenerator = null;
    protected form f = null;
    protected Double start = 0.0;
    protected Double height = 0.0;
    protected Double width = 0.0;
    protected Double scale = 1000.0;
    protected Double task_hei = 20.0;
    protected List<task> tasks = null;

    public svggen(task_service serv) {
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        String svgNS = "http://www.w3.org/2000/svg";
        this.doc = domImpl.createDocument(svgNS, "svg", null);
        this.ctx = SVGGeneratorContext.createDefault(this.doc);
        this.svgGenerator = new SVGGraphics2D(ctx, false);

        this.f = new form((Graphics2D)this.svgGenerator);
        this.tasks = serv.getTasks();
        this.start = serv.getStart();
        this.width = (serv.getEnd() - this.start) * this.scale;
        this.height = serv.getNb_task() * this.task_hei;
    }

    public Double getStart() {
        return this.start;
    }

    public Double getTask_hei() {
        return this.task_hei;
    }

    public Double getHeight() {
        return this.height;
    }

    public Double getWidth() {
        return this.width;
    }

    public Double getScale() {
        return this.scale;
    }

    public List<task> getTasks() {
        return this.tasks;
    }

    public form getForm() {
        return this.f;
    }

    public void setCanvas() {
        this.svgGenerator.setSVGCanvasSize(new Dimension(this.width.intValue()+1+100, this.height.intValue()+1+50));

        this.svgGenerator.translate(0, 25);
        this.setText();
        this.svgGenerator.translate(50, 0);
        this.setTInfo();
    }

    private void setText() {
        int h = 0;
        for(task t : this.tasks) {
            this.svgGenerator.drawString("Task"+t.getName()+"  TID:"+t.getId()+": CPU:"+t.getCpu_id(), 10.0f, (float)(this.task_hei*(h+0.5)));
            this.svgGenerator.drawString("(P:"+t.getPeriod()/1000.0+"ms D:"+t.getDeadline()/1000.0+"ms)", 5.0f, (float)(this.task_hei*(h+0.75)));
            h+=1.0;
        }
    }

    private void setTInfo() {
        int h = 0;
        int period = 0, deadline = 0;
        Double start = null;
        for(task t : this.tasks) {
            period = t.getPeriod();
            deadline = t.getDeadline();
            start = t.getStart();
            if(period != 0 && deadline != 0 && start!=null) for(int nb_period=0; ((start-this.start+((nb_period/1000000.0)*period))*this.scale)<this.width; nb_period++) {
                this.f.wakeLine((start - this.start + (nb_period/1000000.0)*period)*this.scale, (1+h)*this.task_hei, h*this.task_hei);
                this.f.deadLine((start - this.start + (nb_period/1000000.0)*deadline)*this.scale, (1+h)*this.task_hei,  h*this.task_hei);
            }
            h++;
        }

        this.f.axis(this.width, this.height, period*this.scale/1000000.0);

        this.svgGenerator.setFont(Font.decode("arial-plain-2"));
        for(int nb_period=0; ((nb_period/1000000.0)*period*this.scale*2)<this.width; nb_period+=1) this.svgGenerator.drawString(""+String.format("%.3f", ((nb_period/1000.0)*period*2))+"ms", (float)((nb_period/1000000.0)*period*this.scale*2.0f), (float)(this.height+5.0f));
        this.f.setFont();

        start = null;
    }

    public void streamOut() throws IOException {
        Boolean useCSS = true;
        try {
            Writer out = new OutputStreamWriter(System.out, "UTF-8");
            this.svgGenerator.stream(out, useCSS);
        } catch (IOException e) {
            System.err.println("streamout.const : file not found ");
        }
        useCSS = null;
    }

}
