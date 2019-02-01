package com.printer;

import java.awt.*;
import java.awt.geom.*;

public class form {
    protected Graphics2D graph = null;
    protected Shape line = null;
    protected Shape rec = null;
    protected Stroke stro = null;
    protected String font = null;

    public form(Graphics2D graph) {
        this.graph = graph;
        this.line = new Line2D.Double();
        this.rec = new Rectangle2D.Double();
        this.stro = new BasicStroke(0.4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{1,0}, 0);
        this.graph.setPaint(Color.black);
        this.font = "arial-plain-4";
        this.setFont();
        this.setStroke();
        this.graph.setBackground(Color.white);
    }

    public void setFont() {
        this.graph.setFont(Font.decode(this.font));
    }

    public void setStroke() {
        this.graph.setStroke(stro);
    }

    public void arrow(String orio, Double x, Double y, Double size) {
        if(orio.equals("up")) {
            ((Line2D.Double)this.line).setLine(x, y, x-size, y+size);
            this.graph.draw(this.line);
            ((Line2D.Double)this.line).setLine(x, y, x+size, y+size);
            this.graph.draw(this.line);
        }
        else if(orio.equals("right")) {
            ((Line2D.Double)this.line).setLine(x, y, x-size, y-size);
            this.graph.draw(this.line);
            ((Line2D.Double)this.line).setLine(x, y, x-size, y+size);
            this.graph.draw(this.line);
        }
        else if(orio.equals("down")) {
            ((Line2D.Double)this.line).setLine(x, y, x-size, y-size);
            this.graph.draw(this.line);
            ((Line2D.Double)this.line).setLine(x, y, x+size, y-size);
            this.graph.draw(this.line);
        }
    }

    public void wakeLine(Double x, Double yh, Double yl) {
        Stroke wakeS = new BasicStroke(0.3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{2,2}, 0);
        this.graph.setStroke(wakeS);
        this.graph.setPaint(Color.blue);
        ((Line2D.Double)this.line).setLine(x, yh, x, yl);
        this.graph.draw(this.line);

        this.graph.setStroke(this.stro);
        this.arrow("up", x, yl, 1.0);

        this.graph.setPaint(Color.black);
        wakeS = null;
    }

    public void deadLine(Double x, Double yh, Double yl) {
        Stroke deadS = new BasicStroke(0.3f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 0, new float[]{3,3}, 0);
        this.graph.setStroke(deadS);
        this.graph.setPaint(Color.red);
        ((Line2D.Double)this.line).setLine(x, yh, x, yl);
        this.graph.draw(this.line);

        this.graph.setStroke(this.stro);
        this.arrow("down", x, yh, 1.0);

        this.graph.setPaint(Color.black);
        deadS = null;
    }

    public void axis(Double width, Double height, Double scale) {
        ((Line2D.Double)this.line).setLine(0.0, height, width, height);
        this.graph.draw(this.line);
        ((Line2D.Double)this.line).setLine(0.0, height, 0.0, 0.0);
        this.graph.draw(this.line);
        this.arrow("right", width, height, 2.0);
        this.arrow("up", 0.0, 0.0, 2.0);

        for(double h=0.0; h*scale<width; h+=1.0) {
            ((Line2D.Double)this.line).setLine(h*scale, height, h*scale, height+2);
            this.graph.draw(this.line);
        }
        for(double h=0.0; h*scale<width; h+=0.20) {
            ((Line2D.Double)this.line).setLine(h*scale, height, h*scale, height+1);
            this.graph.draw(this.line);
        }
    }

    public void job(Double x, Double y, Double width, Double height, Color col) {
        ((Rectangle2D.Double)this.rec).setRect(x, y, width, height);
        this.graph.setPaint(col);
        this.graph.fill(rec);
        this.graph.setPaint(Color.black);
        this.graph.draw(rec);
    }
}
