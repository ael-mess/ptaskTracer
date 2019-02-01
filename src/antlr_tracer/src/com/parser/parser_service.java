package com.parser;
import com.event.*;

import org.antlr.v4.runtime.*;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class parser_service {
    protected List<trace> traces = new ArrayList<>();
    protected String file_name = null;

    public parser_service(String file_name) throws IOException {
        try {
            CharStream in = CharStreams.fromFileName(file_name);
            traceLexer lexer = new traceLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            traceParser parser = new traceParser(tokens);
            this.traces = parser.start().traces;
        } catch (IOException e) {
               System.err.println("parser_service.const : file not found "+ file_name);
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
