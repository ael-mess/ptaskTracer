parser grammar traceParser;
options {tokenVocab=traceLexer;}

@members { List<trace> traces = new ArrayList<>(); }

start : (timestamp HOSTNAME trace)* EOF;


timestamp returns [ time_trace t ] : 
	(LBRA h=INT COL m=INT COL s=INT DOT os=INT RBRA LPAR PLUS delta=DELTA RPAR) { $t = new time_trace($h.int,$m.int,$s.int,$os.int,$delta.text); } ;

trace returns [ trace tr ] :
    ENTRY_CLONE COL LCBRA header RCBRA COM LCBRA context RCBRA COM LCBRA fields_entry_clone RCBRA { $tr = new clone_entry($cpu_id,$cntx[0],$cntx[1],$t,$field_enc[0],$field_enc[1],$field_enc[2],$field_enc[3]); }
    | EXIT_CLONE COL LCBRA header RCBRA COM LCBRA context RCBRA COM LCBRA fields_exit_clone RCBRA { $tr = new clone_exit($cpu_id,$cntx[0],$cntx[1],$field_exc); }
    | SCHED_SWITCH COL LCBRA header RCBRA COM LCBRA context RCBRA COM LCBRA fields_sched_switch RCBRA { $tr = new sched_switch($cpu_id,$cntx[0],$cntx[1],$field_ss[0],$field_ss[4],$field_ss[1],$field_ss[5],$field_ss[2],$field_ss[6],$field_ss[3]); }
    | PTASK_TRACEPOINT COL LCBRA header RCBRA COM LCBRA fields_ptask_tracepoint RCBRA { $tr = new ptask_tracepoint($cpu_id,$field_pt[2],$field_pt[3],$field_pt[0],$field_pt[1],$field_pt[4],$field_pt[5],$field_pt[6],$field_pt[7],$field_pt[8]); }
    ;

header returns [ Integer cpu_id ] :
	FIELD1 EQ cpuid=INT { $cpu_id = $cpuid.int; } ;
context returns [ List<Integer> cntx ] :
	FIELD2 EQ pid=INT COM FIELD3 EQ tid=INT
		{ $cntx = new ArrayList(2);
		$cntx.add($pid.int);
		$cntx.add($tid.int);} ;
fields_exit_clone returns [ Integer field_exc ] :
	FIELD4 EQ ret=INT {$field_exc = $ret.int; } ;
fields_entry_clone returns [ List<String> field_enc ] :
	FIELD5 EQ clone_flag=HEX COM FIELD6 EQ newsp=HEX COM FIELD7 EQ ptid=HEX COM FIELD8 EQ ctid=HEX
		{ $field_enc = new ArrayList(4);
		$field_enc.add($clone_flag.text);
		$field_enc.add($newsp.text);
		$field_enc.add($ptid.text);
		$field_enc.add($ctid.text); } ;
fields_sched_switch returns [ List<Object> field_ss ] : 
	FIELD9 EQ comm=COMM COM FIELD10 EQ prev_tid=INT COM FIELD11 EQ prev_prio=sint COM FIELD12 EQ prev_state=INT COM FIELD13 EQ comm=COMM COM FIELD14 EQ next_tid=INT COM FIELD15 EQ next_prio=sint
		{ $field_ss = new ArrayList(7); 
		$field_ss.add((Object)$comm.text);
		$field_ss.add((Object)$prev_tid.int);
		$field_ss.add((Object)$prev_prio.text);
		$field_ss.add((Object)$prev_state.int);
		$field_ss.add((Object)$comm.text);
		$field_ss.add((Object)$next_tid.int);
		$field_ss.add((Object)$next_prio.text); } ;
fields_ptask_tracepoint returns [ List<Object> field_pt ] :
	FIELD16 EQ ptask_flag=PTASK_FLAG COM FIELD18 EQ ptask_state=PTASK_STATE COM FIELD19 EQ pid=INT COM FIELD20 EQ tid=INT COM FIELD21 EQ ptask_index=INT COM FIELD22 EQ times=INT COM FIELD23 EQ ptask_prio=INT COM FIELD24 EQ ptask_peri=INT COM FIELD25 EQ ptask_dead=INT
		{ $field_pt = new ArrayList(9); 
		$field_pt.add((Object)$ptask_flag.text);
		$field_pt.add((Object)$ptask_state.text);
		$field_pt.add((Object)$pid.int);
		$field_pt.add((Object)$tid.int);
		$field_pt.add((Object)$ptask_index.int);
		$field_pt.add((Object)$times.int);
		$field_pt.add((Object)$ptask_prio.int);
		$field_pt.add((Object)$ptask_peri.int);
		$field_pt.add((Object)$ptask_dead.int); } ;

sint : INT|SINT;
