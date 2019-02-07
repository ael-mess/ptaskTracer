lexer grammar traceLexer;

@lexer::header {package com.parser;}

EQ :                '=';
PLUS :              '+';
DOT :               '.';
COL :               ':';
COM :               ',';
LBRA :              '[';
RBRA :              ']';
LPAR :              '(';
RPAR :              ')';
LCBRA :             '{';
RCBRA :             '}';
QUOT :              '"';

PTASK_TRACEPOINT :  'ptask_provider:ptask_tracepoint';
SCHED_SWITCH :      'sched_switch';
EXIT_CLONE :        'syscall_exit_clone';
ENTRY_CLONE :       'syscall_entry_clone';

CPU_ID :            'cpu_id';
PID :               'pid';
TID :               'tid';
RET :               'ret';
CLONE_FLAG :        'clone_flags';
NEWSP :             'newsp';
PARENT_TID :        'parent_tid';
CHILD_TID :         'child_tid';
PREV_COMM :         'prev_comm';
PREV_TID :          'prev_tid';
PREV_PRIO :         'prev_prio';
PREV_STATE :        'prev_state';
NEXT_COMM :         'next_comm';
NEXT_TID :          'next_tid';
NEXT_PRIO :         'next_prio';
PTASK_FLAG_F :      'ptask_flag';
PTASK_STATE_F :     'ptask_state';
PTASK_PID :         'ptask_pid';
PTASK_TID :         'ptask_tid';
PTASK_INDEX :       'ptask_index';
PTASK_TIME :        'ptask_time';
PTASK_PRIORITY :    'ptask_priority';
PTASK_PERIOD :      'ptask_period';
PTASK_DEADLINE :    'ptask_deadline';

PTASK_STATE :       QUOT('b_wait_period'|'e_wait_period'|'b_wait_activation'|'e_wait_activation'|'creation')QUOT;
PTASK_FLAG :        QUOT('DEFERRED'|'NOW')QUOT;

DELTA :             ([0-9]'.'[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9])|'?.?????????';
INT :               [0-9]+;
SINT :              '-'[0-9]+;
HEX :               '0x'[0-9A-F]+;
ID :                QUOT[A-Za-z0-9/:. _-]+QUOT;
HOSTNAME :          [A-Za-z0-9-]+;

WS :                [ \t\r\n]+ -> skip;
