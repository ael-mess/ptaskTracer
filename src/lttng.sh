#!/bin/bash

sudo rm -rf /root/lttng-traces/ball_*
sudo lttng create ball_context
sudo lttng enable-event --kernel --syscall clone
sudo lttng enable-event --kernel sched_switch
sudo lttng enable-event --userspace ptask_provider:ptask_tracepoint
#sudo lttng add-context --userspace --type=pthread_id --type=perf:thread:instructions
sudo lttng add-context --kernel --type=pid --type=tid
sudo lttng track --kernel --pid=`ps axf | grep ball | grep -v grep | grep -v sudo |cut -d" " -f1`
sudo sleep 3
sudo lttng start
sudo sleep 5
sudo lttng stop
sudo lttng destroy
sudo sleep .5
sudo babeltrace /root/lttng-traces/ball_context* | sed '/prev_comm\ =\ \"ball\".*next_comm\ =\ \"ball\"\|clone\|ptask_tracepoint/!d'
