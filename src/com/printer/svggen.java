/****************************************************************************
* Class:       svggen()                                                      *
* Parameters:  svg generation parameters                                     *
* Autor:       ael-mess                                                      *
* Description: creates svg document and sets the canvas and the infos        *
****************************************************************************/

package com.printer;
import com.task.*;

import java.io.Writer;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.NullPointerException;

import java.util.ArrayList;
import java.util.List;

import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Font;

import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.anim.dom.SVGDOMImplementation;

import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;

public class svggen {
    protected SVGDocument doc = null;
    protected SVGGeneratorContext ctx = null;
    protected SVGGraphics2D svgGenerator = null;
    protected form f = null;
    protected Double start = 0.0;
    protected Double height = 0.0;
    protected Double width = 0.0;
    protected Double scale = 1000.0;
    protected Double task_hei = 20.0;
    protected List<task> tasks = null;
    protected List<app> os = null;
    protected List<Integer> cpu = null;
    protected Writer out = null;
    protected app main = null;
    protected Boolean osactive = true;
    protected Boolean percpu = false;
    protected Integer curH = 0;

    public svggen(task_service serv, String out) throws IOException, NullPointerException {
        try {
            DOMImplementation domImpl = SVGDOMImplementation.getDOMImplementation();
            String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;//"http://www.w3.org/2000/svg";
            this.doc = (SVGDocument) domImpl.createDocument(svgNS, "svg", null);
            this.ctx = SVGGeneratorContext.createDefault(this.doc);
            ctx.setComment("SVG created for ptask apps");
            this.svgGenerator = new SVGGraphics2D(ctx, false);

            this.f = new form((Graphics2D)this.svgGenerator);
            this.tasks = serv.getTasks();
            this.main = serv.getMain_task();
            this.os = serv.getOs_task();
            this.cpu = serv.getCpu();
            if(this.main.getStart()==null) this.start = this.os.get(0).getStart();
            else this.start = this.main.getStart();
            this.width = (serv.getEnd() - this.start);

            this.out = new FileWriter(out);
        } catch(IOException e) {
            throw new FileNotFoundException("SVG file "+out+" can not be created");
        } catch(NullPointerException e) {
            throw new NullPointerException("Null pointer parameter");
        }
    }

    public SVGDocument getDoc() {
        return this.doc;
    }

    public SVGGraphics2D getGraph() {
        return this.svgGenerator;
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

    public List<app> getOs() {
        return this.os;
    }

    public app getApp() {
        return this.main;
    }

    public Integer getCurH() {
        return this.curH;
    }

    public List<Integer> getCpu() {
        return this.cpu;
    }

    public void setHeight() {
        int nbt = 0, oss = 0;
        if(this.percpu) nbt = this.cpu.size();
        else nbt = tasks.size();
        if(this.osactive) oss = this.os.size();
        else oss = 1;
        this.height = (nbt + 1 + oss)* this.task_hei;
    }

    public void setWidth() {
        this.width = this.width * this.scale;
    }

    public Boolean isOsactive() {
        return this.osactive;
    }

    public Boolean isPercpu() {
        return this.percpu;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public void setTask_hei(Double th) {
        this.task_hei = th;
    }

    public void setOsactive(Boolean act) {
        this.osactive = act;
    }

    public void setPercpu(Boolean act) {
        this.percpu = act;
    }

    // setting the canvas with 50/2 height and 150/2 width plus
    public void setCanvas() throws NullPointerException {
        this.setHeight();
        this.setWidth();
        this.svgGenerator.setSVGCanvasSize(new Dimension(this.width.intValue()+1+150, this.height.intValue()+1+50));

        this.svgGenerator.translate(0, 25);
        this.setText();
        this.svgGenerator.translate(75, 0);
        this.setTInfo();
    }

    // setting info text for canvas (param: h, dist from x=0)
    private void setText() throws NullPointerException  {
        this.curH = 0;
        if(this.osactive) for(app proc: this.os) {
            this.svgGenerator.drawString("OS CPU:"+proc.getId(), 5.0f, (float)(this.task_hei*(this.curH+0.5)));
            this.curH++;
        }
        else this.curH++;

        this.svgGenerator.drawString("Main task "+this.main.getName()+"  PID:"+this.main.getId(), 3.0f, (float)(this.task_hei*(this.curH+0.5)));
        this.curH++;

        if(this.percpu) for(Integer id : this.cpu) {
                this.svgGenerator.drawString("CPU"+id, 3.0f, (float)(this.task_hei*(this.curH+0.5)));
                this.curH++;
        }
        else for(Integer id : this.cpu) for(task t : this.tasks) if(t.getCpu_id().equals(id)) {
            this.svgGenerator.drawString("Task"+t.getName()+"  TID:"+t.getId()+": CPU:"+t.getCpu_id(), 3.0f, (float)(this.task_hei*(this.curH+0.5)));
            this.svgGenerator.drawString("(P:"+t.getPeriod()/1000.0+"ms D:"+t.getDeadline()/1000.0+"ms)", 3.0f, (float)(this.task_hei*(this.curH+0.75)));
            this.curH++;
        }
    }

    // setting period and deadline infos (param: h)
    // using the last period for axis info (1 for 2 period)
    private void setTInfo() throws NullPointerException {
        this.curH = 0;
        int period = 0, deadline = 0;
        Double start = null;
        if(!this.percpu) {
            if(this.osactive) this.curH = 1 + os.size();
            else this.curH = 2;
            for(Integer id : this.cpu) {
                this.f.line(this.curH*this.task_hei, this.width);
                for(task t : this.tasks) if(t.getCpu_id().equals(id)) {
                    period = t.getPeriod();
                    deadline = t.getDeadline();
                    start = t.getStart();
                    if(period != 0 && deadline != 0 && start!=null) for(int nb_period=0; ((start-this.start+((nb_period/1000000.0)*period))*this.scale)<this.width; nb_period++) {
                        this.f.wakeLine((start - this.start + (nb_period/1000000.0)*period)*this.scale, (1+this.curH)*this.task_hei, this.curH*this.task_hei);
                        this.f.deadLine((start - this.start + ((nb_period+1)/1000000.0)*deadline)*this.scale, (1+this.curH)*this.task_hei,  this.curH*this.task_hei);
                    }
                this.curH++;
                }
            }
        }
        else this.curH++;

        period = this.tasks.get(0).getPeriod();
        this.f.axis(this.width, this.height, period*this.scale/1000000.0);

        this.svgGenerator.setFont(Font.decode("arial-plain-3"));
        for(int nb_period=0; ((nb_period/1000000.0)*period*this.scale*2)<this.width; nb_period+=1)
            this.svgGenerator.drawString(""+String.format("%.3f", ((nb_period/1000.0)*period*2))+"ms", (float)((nb_period/1000000.0)*period*this.scale*2.0f), (float)(this.height+5.0f));
        this.f.setFont();
    }

    public void streamOut(Element root) throws NullPointerException, IOException {
        try {
            this.svgGenerator.stream(root, this.out);
            this.out.close();
        } catch(IOException e) {
            throw new FileNotFoundException("SVG file "+this.out+" can not be closed");
        }
    }
}
