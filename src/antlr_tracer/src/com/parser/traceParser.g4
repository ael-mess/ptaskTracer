parser grammar traceParser;
@header {
  package com.parser;
  import com.event.*;
}
options {tokenVocab=traceLexer;}


start returns [ List<trace> traces ] : { $traces = new ArrayList<>(); } (trace { $traces.add($trace.tr); })* EOF;

trace returns [ trace tr ] :
    timestamp HOSTNAME ENTRY_CLONE COL LCBRA header RCBRA COM LCBRA context RCBRA COM LCBRA fields_entry_clone RCBRA { $tr = new clone_entry($timestamp.t,$header.cpu_id,$context.cntx.get(0),$context.cntx.get(1),$fields_entry_clone.field_enc.get(0),$fields_entry_clone.field_enc.get(1),$fields_entry_clone.field_enc.get(2),$fields_entry_clone.field_enc.get(3)); }
    | timestamp HOSTNAME EXIT_CLONE COL LCBRA header RCBRA COM LCBRA context RCBRA COM LCBRA fields_exit_clone RCBRA { $tr = new clone_exit($timestamp.t,$header.cpu_id,$context.cntx.get(0),$context.cntx.get(1),$fields_exit_clone.field_exc); }
    | timestamp HOSTNAME SCHED_SWITCH COL LCBRA header RCBRA COM LCBRA context RCBRA COM LCBRA fields_sched_switch RCBRA { $tr = new sched_switch($timestamp.t,$header.cpu_id,$context.cntx.get(0),$context.cntx.get(1),$fields_sched_switch.field_ss.get(0),$fields_sched_switch.field_ss.get(4),$fields_sched_switch.field_ss.get(1),$fields_sched_switch.field_ss.get(5),$fields_sched_switch.field_ss.get(2),$fields_sched_switch.field_ss.get(6),$fields_sched_switch.field_ss.get(3)); }
    | timestamp HOSTNAME PTASK_TRACEPOINT COL LCBRA header RCBRA COM LCBRA fields_ptask_tracepoint RCBRA { $tr = new ptask_tracepoint($timestamp.t,$header.cpu_id,$fields_ptask_tracepoint.field_pt.get(2),$fields_ptask_tracepoint.field_pt.get(3),$fields_ptask_tracepoint.field_pt.get(0),$fields_ptask_tracepoint.field_pt.get(1),$fields_ptask_tracepoint.field_pt.get(4),$fields_ptask_tracepoint.field_pt.get(5),$fields_ptask_tracepoint.field_pt.get(6),$fields_ptask_tracepoint.field_pt.get(7),$fields_ptask_tracepoint.field_pt.get(8)); }
    ;


timestamp returns [ time_trace t ] :
	(LBRA h=INT COL m=INT COL s=INT DOT os=INT RBRA LPAR PLUS delta=DELTA RPAR) { $t = new time_trace($h.int,$m.int,$s.int,$os.text,$delta.text); } ;

header returns [ Integer cpu_id ] :
	FIELD1 EQ cpuid=INT { $cpu_id = $cpuid.int; } ;
context returns [ List<Integer> cntx ] :
	FIELD2 EQ pid=INT COM FIELD3 EQ tid=INT
		{ $cntx = new ArrayList<Integer>(2);
		$cntx.add(0,$pid.int);
		$cntx.add(1,$tid.int);} ;
fields_exit_clone returns [ Integer field_exc ] :
	FIELD4 EQ ret=INT {$field_exc = $ret.int; } ;
fields_entry_clone returns [ List<String> field_enc ] :
	FIELD5 EQ clone_flag=HEX COM FIELD6 EQ newsp=HEX COM FIELD7 EQ ptid=HEX COM FIELD8 EQ ctid=HEX
		{ $field_enc = new ArrayList<String>(4);
		$field_enc.add(0,$clone_flag.text);
		$field_enc.add(1,$newsp.text);
		$field_enc.add(2,$ptid.text);
		$field_enc.add(3,$ctid.text); } ;
fields_sched_switch returns [ List<String> field_ss ] :
	FIELD9 EQ comm=COMM COM FIELD10 EQ prev_tid=INT COM FIELD11 EQ prev_prio=sint COM FIELD12 EQ prev_state=INT COM FIELD13 EQ comm=COMM COM FIELD14 EQ next_tid=INT COM FIELD15 EQ next_prio=sint
		{ $field_ss = new ArrayList<String>(7);
		$field_ss.add(0,$comm.text);
		$field_ss.add(1,$prev_tid.text);
		$field_ss.add(2,$prev_prio.text);
		$field_ss.add(3,$prev_state.text);
		$field_ss.add(4,$comm.text);
		$field_ss.add(5,$next_tid.text);
		$field_ss.add(6,$next_prio.text); } ;
fields_ptask_tracepoint returns [ List<String> field_pt ] :
	FIELD16 EQ ptask_flag=PTASK_FLAG COM FIELD18 EQ ptask_state=PTASK_STATE COM FIELD19 EQ pid=INT COM FIELD20 EQ tid=INT COM FIELD21 EQ ptask_index=INT COM FIELD22 EQ times=INT COM FIELD23 EQ ptask_prio=INT COM FIELD24 EQ ptask_peri=INT COM FIELD25 EQ ptask_dead=INT
		{ $field_pt = new ArrayList<String>(9);
		$field_pt.add(0,$ptask_flag.text);
		$field_pt.add(1,$ptask_state.text);
		$field_pt.add(2,$pid.text);
		$field_pt.add(3,$tid.text);
		$field_pt.add(4,$ptask_index.text);
		$field_pt.add(5,$times.text);
		$field_pt.add(6,$ptask_prio.text);
		$field_pt.add(7,$ptask_peri.text);
		$field_pt.add(8,$ptask_dead.text); } ;

sint : INT|SINT;
