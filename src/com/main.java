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
        if(args.length == 7) {
            parser_service parser = new parser_service(args[6]+"/build/"+args[0]+"_raw.txt");
            System.out.println("[\u001B[32mOK\u001B[0m]");

            System.out.println("\n> Analysing the data...");
            task_service tasks = new task_service(parser.getTraces(), args);
            System.out.println("[\u001B[32mOK\u001B[0m]");

            System.out.println("\n> Generating the SVG...");
            printer_service printer = new printer_service(tasks, args[6]+"/build/"+args[0]+".svg", args);
            System.out.println("[\u001B[32mOK\u001B[0m]");

            //System.out.println(parser.description());
            //System.out.println(tasks.description());
            //tasks.check();
        }
        else throw new IllegalArgumentException("Bad arguments or number of arguments");
    }
}
