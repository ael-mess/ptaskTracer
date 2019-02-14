# ptaskTracer

Tracing tool for periodic and aperiodic real-time tasks of ptask API.

- Version 1.0, February 2018

## Introduction

ptaskTracer is a tool that traces the execution of periodic and aperiodic tasks created with the [API ptask](https://github.com/glipari/ptask), analyses the traces, and generates a SVG of the scheduling of the threads. The tool uses :
- [LTTng](https://github.com/lttng), an open source software toolkit which can be used to trace the Linux kernel, user applications, and user libraries. 
- [ANTLR4](https://github.com/antlr/antlr4), a parser generator for reading, processing, executing, or translating structured text or binary files.
- [Batik](https://github.com/apache/batik), a Java-based toolkit for applications or applets that want to use SVG images format.

## Requirements

### Requirements of ptask

- Allegro 4 libraries
- CMake 3.1+

### Requirements of the tool

- Java
- LTTng 2.10
- ANTLR4
- Batik and w3c libraries

## Installation

To install Lttng 2.10, download ANTLR4 4.7.2, Batik 1.10 and w3c jar lib libraries, compile and install the tool, run (from the repository ):

```shell
$ sudo ./install.sh
```

On Ubuntu systems, add stable 2.10 PPA repository before :

```shell
$ sudo apt-add-repository ppa:lttng/stable-2.10
$ sudo apt-get update
$ sudo ./install.sh
```

Make sure <code>lttng-tools</code>, <code>lttng-modules</code>, and <code>liblttng-ust</code> are installed before running the application.

## Execution 

Create LTTng daemon session :

```shell
$ sudo lttng-sessiond --daemonize
```

Start the application :

```shell
$ sudo ptaskTracer -f|--file [APP_NAME] <-p|--path APP_PATH> <-t|--time TRACING_TIME> <-s|--scale PRINT_SCALE> <-h|--theight TASK_HEIGTH> <-os|--osapp> <-g|--group> <-o|--output OUTPUT>
```

or

```shell
$ sudo ptaskTracer --old -f|--file [APP_NAME] <-s|--scale PRINT_SCALE> <-h|--theight TASK_HEIGTH> <-os|--osapp> <-g|--group> <-i|--input INPUT>
```

With the option <code>--old</code>, no tracing is performed, the trace analysed will be  <code>INPUT/build/[APP_NAME]_raw.txt </code>

For help, type :

```shell
$ ptaskTracer --help
```

These parameters are optional :

-  <code><-o|--output OUTPUT></code> to specifie directory where all output in build/ is generated
-  <code><-i|--input INPUT></code> to specifie directory of the old <code>build/[APP_NAME]_raw.txt</code>
-  <code><-t|--time TRACING_TIME></code> for tracing time in second, must be unsigned double  (default: 4s)
-  <code><-s|--scale PRINT_SCALE></code> for printing scale, must be unsigned double  (default: 1000.0px/s)
-  <code><-h|--theight TASK_HEIGTH></code> for printing task height in px, must be unsigned double  (default: 20.0px)
-  <code><-os|--osapp></code> to trace system activity (default: false)
-  <code><-g|--group></code> to group tasks by cpu (default: false)
