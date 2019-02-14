/****************************************************************************
* Class:       form()                                                        *
* Parameters:  graphics parameters                                           *
* Autor:       ael-mess                                                      *
* Description: represents basic forms                                        *
****************************************************************************/

package com.printer;

import java.awt.*;
import java.awt.geom.*;

import java.util.Random;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.DOMImplementation;

import org.apache.batik.anim.dom.SVGDOMImplementation;

public class form {
    protected Graphics2D graph = null;
    protected Shape line = null;
    protected Shape rec = null;
    protected Element recNS = null;
    protected Element lineNS = null;
    protected Element textNS1 = null;
    protected Element textNS2 = null;
    protected Stroke stro = null;
    protected String font = null;

    public form(Graphics2D graph) {
        this.graph = graph;
        this.line = new Line2D.Double();
        this.rec = new Rectangle2D.Double();
        this.stro = new BasicStroke(0.4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{1,0}, 0);
        this.graph.setPaint(Color.black);
        this.font = "arial-plain-5";
        this.setFont();
        this.setStroke();
        this.graph.setBackground(Color.white);
    }

    public Shape getRec() {
        return this.rec;
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

    public void line(Double y, Double width) {
        Stroke st = new BasicStroke(0.3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{4,4}, 0);
        this.graph.setStroke(st);
        this.graph.setPaint(Color.black);
        ((Line2D.Double)this.line).setLine(0.0, y, width, y);
        this.graph.draw(this.line);
        this.graph.setStroke(this.stro);
    }

    public void lineNS(Element root, SVGDocument doc, Double y, Double width) {
        this.lineNS = doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "line");
        this.lineNS.setAttributeNS(null, "transform", "translate(75,25)");
        this.lineNS.setAttributeNS(null, "x1", "0");
    	this.lineNS.setAttributeNS(null, "y1", Double.toString(y));
        this.lineNS.setAttributeNS(null, "x2", Double.toString(width));
    	this.lineNS.setAttributeNS(null, "y2", Double.toString(y));
    	this.lineNS.setAttributeNS(null, "stroke", "black");
    	this.lineNS.setAttributeNS(null, "stroke-dasharray", "4");
        this.lineNS.setAttributeNS(null, "stroke-width", "0.2px");

        root.appendChild(this.lineNS);
    }

    public void jobNS(Element root, SVGDocument doc, Double x, Double y, Double width, Double height, String col, String text1, String text2) {
        Random rand = new Random();
        int id = rand.nextInt(Integer.MAX_VALUE - 1) + 0;

        this.recNS = doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "rect");
        this.recNS.setAttributeNS(null, "id", ""+id);
        this.recNS.setAttributeNS(null, "transform", "translate(75,25)");
        this.recNS.setAttributeNS(null, "x", Double.toString(x));
    	this.recNS.setAttributeNS(null, "y", Double.toString(y));
    	this.recNS.setAttributeNS(null, "width", Double.toString(width));
    	this.recNS.setAttributeNS(null, "height", Double.toString(height));
    	this.recNS.setAttributeNS(null, "fill", col);
    	this.recNS.setAttributeNS(null, "stroke", "black");
    	this.recNS.setAttributeNS(null, "stroke-width", "0.5px");

        root.appendChild(this.recNS);
        this.jobInfoNS(root, doc, x, y, width, height, id, text1, text2);
        rand = null;
    }

    private void jobInfoNS(Element root, SVGDocument doc, Double x, Double y, Double width, Double height, Integer id, String text1, String text2) {
        this.recNS = doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "rect");
        this.recNS.setAttributeNS(null, "transform", "translate(75,25)");
        this.recNS.setAttributeNS(null, "x", Double.toString(x));
    	this.recNS.setAttributeNS(null, "y", Double.toString(y));
    	this.recNS.setAttributeNS(null, "width", Double.toString(width));
    	this.recNS.setAttributeNS(null, "height", Double.toString(height));
    	this.recNS.setAttributeNS(null, "fill", "white");
    	this.recNS.setAttributeNS(null, "stroke", "black");
    	this.recNS.setAttributeNS(null, "stroke-width", "0.5px");
        this.recNS.setAttributeNS(null, "visibility", "hidden");
        root.appendChild(this.recNS);

        Element back = doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "rect");
        //back.setAttributeNS(null, "transform", "translate(50,25)");
        back.setAttributeNS(null, "x", Double.toString(x));
        back.setAttributeNS(null, "y", Double.toString(y));
        back.setAttributeNS(null, "width", "150");
        back.setAttributeNS(null, "height", "12");
        back.setAttributeNS(null, "fill", "white");
        back.setAttributeNS(null, "stroke", "black");
        back.setAttributeNS(null, "transform", "translate(0,13)");
        back.setAttributeNS(null, "stroke-width", "0.5px");
        back.setAttributeNS(null, "visibility", "hidden");
        root.appendChild(back);

        this.textNS1 = doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "text");
        this.textNS1.setAttributeNS(null, "transform", "translate(75,25)");
        this.textNS1.setAttributeNS(null, "x", Double.toString(x));
        this.textNS1.setAttributeNS(null, "y", Double.toString(y-7.0));
        this.textNS1.setAttributeNS(null, "stroke", "none");
        this.textNS1.setAttributeNS(null, "font-size", "5px");
        this.textNS1.setAttributeNS(null, "text-anchor", "middle");
        this.textNS1.setAttributeNS(null, "font-family", "arial");
        this.textNS1.setAttributeNS(null, "visibility", "hidden");
        this.textNS1.setTextContent(text1);
        root.appendChild(this.textNS1);
        this.textNS2 = doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "text");
        this.textNS2.setAttributeNS(null, "transform", "translate(75,25)");
        this.textNS2.setAttributeNS(null, "x", Double.toString(x));
        this.textNS2.setAttributeNS(null, "y", Double.toString(y-2.0));
        this.textNS2.setAttributeNS(null, "stroke", "none");
        this.textNS2.setAttributeNS(null, "font-size", "5px");
        this.textNS2.setAttributeNS(null, "text-anchor", "middle");
        this.textNS2.setAttributeNS(null, "font-family", "arial");
        this.textNS2.setAttributeNS(null, "visibility", "hidden");
        this.textNS2.setTextContent(text2);
        root.appendChild(this.textNS2);

        Element set = doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "set");
        set.setAttributeNS(null, "attributeName", "visibility");
        set.setAttributeNS(null, "from", "hidden");
        set.setAttributeNS(null, "to", "visible");
        set.setAttributeNS(null, "begin", ""+id+".mouseover");
        set.setAttributeNS(null, "end", ""+id+".mouseout");
        this.recNS.appendChild(set);
        set = doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "set");
        set.setAttributeNS(null, "attributeName", "visibility");
        set.setAttributeNS(null, "from", "hidden");
        set.setAttributeNS(null, "to", "visible");
        set.setAttributeNS(null, "begin", ""+id+".mouseover");
        set.setAttributeNS(null, "end", ""+id+".mouseout");
        back.appendChild(set);
        set = doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "set");
        set.setAttributeNS(null, "attributeName", "visibility");
        set.setAttributeNS(null, "from", "hidden");
        set.setAttributeNS(null, "to", "visible");
        set.setAttributeNS(null, "begin", ""+id+".mouseover");
        set.setAttributeNS(null, "end", ""+id+".mouseout");
        this.textNS1.appendChild(set);
        set = doc.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "set");
        set.setAttributeNS(null, "attributeName", "visibility");
        set.setAttributeNS(null, "from", "hidden");
        set.setAttributeNS(null, "to", "visible");
        set.setAttributeNS(null, "begin", ""+id+".mouseover");
        set.setAttributeNS(null, "end", ""+id+".mouseout");
        this.textNS2.appendChild(set);

        set = null;
        back = null;
    }
}
