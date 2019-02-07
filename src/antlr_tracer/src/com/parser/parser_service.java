/****************************************************************************
* Class:       parser_service()                                              *
* Parameters:  trace file name of the trace                                  *
* Autor:       ael-mess                                                      *
* Description: import traces from file to this java list (of trace objt)     *
****************************************************************************/

package com.parser;
import com.event.*;

import org.antlr.v4.runtime.*;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.NullPointerException;
import java.lang.OutOfMemoryError;

public class parser_service {
    protected List<trace> traces = new ArrayList<>();
    protected String file_name = null;

    public parser_service(String file_name) throws IOException, OutOfMemoryError {
        try {
            CharStream in = CharStreams.fromFileName(file_name);
            traceLexer lexer = new traceLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            traceParser parser = new traceParser(tokens);
            this.traces = parser.start().traces;

            System.out.println(""+this.traces.size()+" events saved in the trace");
            if(this.traces==null) throw new NullPointerException("Traces List null pointer");
        } catch (IOException e) {
            throw new FileNotFoundException("Trace file "+file_name+" not found");
        } catch (OutOfMemoryError e) {
            System.err.println("Exception in thread \"main\" java.lang.OutOfMemoryError: Java heap space: failed reallocation (too many events in the trace file)\n        at com.parser.parser_service.<init>(parser_service.java:38)\n        at com.main.main(main.java:21)");
            System.exit(2);
        }
    }

    public List<trace> getTraces() {
        return this.traces;
    }

    public String description() {
        String ret = "";
        for(trace tr : this.traces) ret += tr.description()+"\n";
        return ret;
    }
}
