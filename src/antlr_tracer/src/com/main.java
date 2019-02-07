/****************************************************************************
* Class:       main()                                                        *
* Parameters:  NA                                                            *
* Autor:       ael-mess                                                      *
* Description: creates services to manage parser, tasks, and printer         *
****************************************************************************/

package com;

import com.parser.*;
import com.event.*;
import com.task.*;
import com.printer.*;

import java.io.IOException;
import java.lang.IllegalArgumentException;

public class main {
    public static void main(String[] args) throws IOException {
        if(args.length == 6) {
            parser_service parser = new parser_service("build/"+args[0]+"_raw.txt");
            System.out.println("[\u001B[32mOK\u001B[0m]");

            System.out.println("\n> Analysing the data...");
            task_service tasks = new task_service(parser.getTraces());
            tasks.Externset(args[0], Integer.valueOf(args[1]));
            System.out.println("[\u001B[32mOK\u001B[0m]");

            System.out.println("\n> Generating the SVG...");
            printer_service printer = new printer_service(tasks, "build/"+args[0]+".svg");
            printer.Externset(Double.valueOf(args[2]), Double.valueOf(args[3]), Boolean.valueOf(args[4]), Boolean.valueOf(args[5]));
            System.out.println("[\u001B[32mOK\u001B[0m]");

            //System.out.println(parser.description());
            //System.out.println(tasks.description());
            //tasks.check();
        }
        else throw new IllegalArgumentException("Bad arguments or number of arguments");
    }
}
