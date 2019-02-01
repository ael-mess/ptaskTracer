package com;

import com.parser.*;
import com.event.*;
import com.task.*;
import com.printer.*;

import java.io.IOException;

public class main {
    public static void main(String[] arg) throws IOException {
        parser_service parser = new parser_service("../mytest.txt"); //secure path
        task_service tasks = new task_service(parser.getTraces());

        //System.out.println(parser.description());
        //tasks.check();
        System.err.println(tasks.description());
        System.err.println("start:"+tasks.getStart()+" end:"+tasks.getEnd()+" nb task:"+tasks.getNb_task());

        printer_service printer = new printer_service(tasks);
    }
}
