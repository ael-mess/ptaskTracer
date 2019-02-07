parser grammar traceParser;
@header {
package com.parser;
import com.event.*;
}
options {tokenVocab=traceLexer;}


start returns [ List<trace> traces ] : { $traces = new ArrayList<>(); } (trace { $traces.add($trace.tr); })* EOF;

trace returns [ trace tr ] :
timestamp HOSTNAME ENTRY_CLONE COL LCBRA header RCBRA COM LCBRA context RCBRA COM LCBRA CLONE_FLAG EQ clone_flag=HEX COM NEWSP EQ newsp=HEX COM PARENT_TID EQ ptid=HEX COM CHILD_TID EQ ctid=HEX RCBRA
        { $tr = new clone_entry($timestamp.t,$header.cpu_id,$context.cntx.get(0),$context.cntx.get(1),$clone_flag.text,$newsp.text,$ptid.text,$ctid.text); }
    | timestamp HOSTNAME EXIT_CLONE COL LCBRA header RCBRA COM LCBRA context RCBRA COM LCBRA RET EQ ret=INT RCBRA
        { $tr = new clone_exit($timestamp.t,$header.cpu_id,$context.cntx.get(0),$context.cntx.get(1),$ret.int); }
    | timestamp HOSTNAME SCHED_SWITCH COL LCBRA header RCBRA COM LCBRA context RCBRA COM LCBRA PREV_COMM EQ prev_comm=comm COM PREV_TID EQ prev_tid=INT COM PREV_PRIO EQ prev_prio=sint COM PREV_STATE EQ prev_state=INT COM NEXT_COMM EQ next_comm=comm COM NEXT_TID EQ next_tid=INT COM NEXT_PRIO EQ next_prio=sint RCBRA
        { $tr = new sched_switch($timestamp.t,$header.cpu_id,$context.cntx.get(0),$context.cntx.get(1),$prev_comm.text,$next_comm.text,$prev_tid.int,$next_tid.int,$prev_prio.text,$next_prio.text,$prev_state.int); }
    | timestamp HOSTNAME PTASK_TRACEPOINT COL LCBRA header RCBRA COM LCBRA PTASK_PID EQ pid=INT COM PTASK_TID EQ tid=INT COM PTASK_INDEX EQ ptask_index=INT COM PTASK_FLAG_F EQ ptask_flag=PTASK_FLAG COM PTASK_STATE_F EQ ptask_state=PTASK_STATE COM PTASK_TIME EQ times=INT COM PTASK_PRIORITY EQ ptask_prio=INT COM PTASK_PERIOD EQ ptask_peri=INT COM PTASK_DEADLINE EQ ptask_dead=INT RCBRA
        { $tr = new ptask_tracepoint($timestamp.t,$header.cpu_id,$pid.int,$tid.int,$ptask_flag.text,$ptask_state.text,$ptask_index.int,$times.text,$ptask_prio.int,$ptask_peri.int,$ptask_dead.int); }
    ;

timestamp returns [ time_trace t ] :
    LBRA h=INT COL m=INT COL s=INT DOT os=INT RBRA LPAR PLUS delta=DELTA RPAR
        { $t = new time_trace($h.int,$m.int,$s.int,$os.text,$delta.text); } ;

header returns [ Integer cpu_id ] :
    CPU_ID EQ cpuid=INT
        { $cpu_id = $cpuid.int; } ;

context returns [ List<Integer> cntx ] :
    PID EQ pid=INT COM TID EQ tid=INT
        { $cntx = new ArrayList<Integer>(2);
        $cntx.add(0,$pid.int);
        $cntx.add(1,$tid.int); } ;

comm : ID;
sint : INT|SINT;
