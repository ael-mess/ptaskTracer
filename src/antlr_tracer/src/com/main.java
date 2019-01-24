package com;

import com.parser.*;
import com.event.*;
import com.task.*;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] arg) {
        parser_service parser = new parser_service("../test2.txt"); //secure path
        task_service tasks = new task_service();

        //System.out.println(parser.description());
        System.out.println("");

        tasks.setTraces(parser.getTraces());
        tasks.setTasks();

        //tasks.check();
        System.out.println(tasks.description());
    }
}
