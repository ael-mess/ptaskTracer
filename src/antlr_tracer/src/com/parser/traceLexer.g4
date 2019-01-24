lexer grammar traceLexer;

@lexer::header {package com.parser;}

PTASK_TRACEPOINT :	'ptask_provider:ptask_tracepoint';
SCHED_SWITCH :		'sched_switch';
EXIT_CLONE :		'syscall_exit_clone';
ENTRY_CLONE :		'syscall_entry_clone';

FIELD1 : 			'cpu_id';
FIELD2 :			'pid';
FIELD3 :			'tid';
FIELD4 :			'ret';
FIELD5 :			'clone_flags';
FIELD6 :			'newsp';
FIELD7 :			'parent_tid';
FIELD8 :			'child_tid';
FIELD9 :			'prev_comm';
FIELD10 :			'prev_tid';
FIELD11 :			'prev_prio';
FIELD12 :			'prev_state';
FIELD13 :			'next_comm';
FIELD14 :			'next_tid';
FIELD15 :			'next_prio';
FIELD16 :			'ptask_flag';
FIELD18 :			'ptask_state';
FIELD19 :			'ptask_pid';
FIELD20 :			'ptask_tid';
FIELD21 :			'ptask_index';
FIELD22 :			'ptask_time';
FIELD23 :			'ptask_priority';
FIELD24 :			'ptask_period';
FIELD25 :			'ptask_deadline';

PTASK_STATE :		'"b_wait_period"'|'"e_wait_period"'|'"b_wait_activation"'|'"e_wait_activation"';
PTASK_FLAG :		'"DEFERRED"'|'"NOW"';
COMM :				'"ball"';

EQ : 				'=';
PLUS :				'+';
DOT :				'.';
COL :				':';
COM :				',';
LBRA :				'[';
RBRA :				']';
LPAR :				'(';
RPAR : 				')';
LCBRA :				'{';
RCBRA :				'}';

DELTA :				[0-9]'.'[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9];
INT : 				[0-9]+;
SINT :				'-'[0-9]+;
HEX :				'0x'[0-9A-F]+;
HOSTNAME :			[A-Za-z0-9-]+;

WS : [ \t\r\n]+ -> skip ;
