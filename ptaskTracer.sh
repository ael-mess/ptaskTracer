#!/bin/bash



#getting root access
prompt=$(sudo -nv 2>&1)
if [ $? -eq 1 ]; then
    echo "root permission required"
    exit 1
fi
VERSION="-1.0"



#parsing arguments
POSITIONAL=()
while [[ $# -gt 0 ]]; do
    key="$1"
    case $key in
        -f|--file)
        APP="$2"
        shift # past argument
        shift # past value
        ;;
        -a|--args)
        ARGS="$2"
        shift # past argument
        shift # past value
        ;;
        -p|--path)
        APPPATH="$2"
        shift
        shift
        ;;
        -t|--time)
        TIME="$2"
        shift
        shift
        ;;
        -s|--scale)
        SCALE="$2"
        shift
        shift
        ;;
        -h|--theight)
        HEIGHT="$2"
        shift
        shift
        ;;
        -o|--output)
        OUT="$2"
        shift
        shift
        ;;
        -i|--input)
        IN="$2"
        shift
        shift
        ;;
        -os|--osapp)
        OS=true
        shift
        ;;
        -g|--group)
        CPU=true
        shift
        ;;
        --old)
        OLD=true
        shift
        ;;
        --help)
        HELP=true
        shift
        ;;
        *) # unknown option
        echo "Unkown argument $1, type ptaskTracer --help"
        POSITIONAL+=("$1")
        exit 1
        shift
        ;;
    esac
done
set -- "${POSITIONAL[@]}" # restore positional parameters



if [[ "$HELP" = true ]]; then # showing help
    echo ""
    echo "ptaskTracer, version ${VERSION}"
    echo "This program uses LTTng 2.10, ANTLR4 4.7.2, Batik 1.10 and w3c JAVA lib"
    echo "Type tracer --help to see this help"
    echo "To run the application type :"
    echo ""
    echo "ptaskTracer -f|--file [APP_NAME] <-p|--path APP_PATH> <-t|--time TRACING_TIME> <-s|--scale PRINT_SCALE> <-h|--theight TASK_HEIGTH> <-os|--osapp> <-g|--group> <-o|--output OUTPUT>"
    echo ""
    echo "or"
    echo ""
    echo "ptaskTracer --old -f|--file [APP_NAME] <-s|--scale PRINT_SCALE> <-h|--theight TASK_HEIGTH> <-os|--osapp> <-g|--group> <-i|--input INPUT>"
    echo ""
    echo "With option --old no tracing is performed, the trace analysed will be build/[APP_NAME]_raw.txt"
    echo "These parameters are optional :"
    echo "<-a|--args ARGS> arguments to the program"
    echo "<-o|--output OUTPUT> to specify directory where all output in build/ is generated"
    echo "<-i|--input INPUT> to specify directory of old build/[APP_NAME]_raw.txt"
    echo "<-t|--time TRACING_TIME> for tracing time in second, must be unsigned double  (default: 4s)"
    echo "<-s|--scale PRINT_SCALE> for printing scale, must be unsigned double  (default: 1000.0px/s)"
    echo "<-h|--theight TASK_HEIGTH> for printing task height in px, must be unsigned double  (default: 20.0px)"
    echo "<-os|--osapp> to trace system activity (default: false)"
    echo "<-g|--group> to group tasks by cpu (default: false)"
    echo ""
    exit 0
fi



echo ""
echo -e "\033[1;33mWelcome to ptaskTracer\033[0m"
echo "> Checking parameter..."
#checking parameters
if [[ -z "$APP" ]]; then # app param
    echo "Unspecified app -f or --file, type ptaskTracer --help for help"
    exit 1
fi
if [[ -n "$OLD" ]]; then # svg option
    if [[ -n "$APPPATH" ]]; then
        echo "[warning] argument ${APPPATH} not needed with --old option"
    fi
    if [[ -n "$TIME" ]]; then
        echo "[warning] argument ${TIME} not needed with --old option"
    fi
    if [[ -n "$IN" ]]; then
        if ! [[ -d "$IN" ]]; then
            echo "Bad -i --input argument, must be directory, type ptaskTracer --help for help"
            exit 1
        else
            echo "Input directory will be ${IN}"
        fi
    else
        echo "Unspecified input -i or --input"
        echo "Using current dirrecory"
        IN="."
    fi
    if [[ -n "$OUT" ]]; then
        echo "[warning] argument ${OUT} not needed with --old option, output direcory will be ${IN}/build"
    fi
    OUT=$IN
else # no svg option
    if [[ -n "$APPPATH" ]]; then
        if ! [[ -d "$APPPATH" ]]; then
            echo "Bad -p --path argument, must be directory, type ptaskTracer --help for help"
            exit 1
        fi
    else
        echo "Unspecified application path -p or --path"
        echo "Using current directory"
        APPPATH="."
    fi
    if [[ -n "$TIME" ]]; then
        if ! [[ $TIME =~ ^[0-9]+([.][0-9]+)?$ ]]; then
            echo "Bad -t --time argument, must be unsigned double, type ptaskTracer --help for help"
            exit 1
        else
            echo "Tracing time set to ${TIME}s"
        fi
    else
        echo "Unspecified tracing time -t or --time"
        echo "Default time set to 4s"
        TIME=4
    fi
    if [[ -n "$OUT" ]]; then
        if ! [[ -d "$OUT" ]]; then
            echo "Bad -o --output argument, must be directory, type ptaskTracer --help for help"
            exit 1
        else
            echo "Output directory will be ${OUT}"
        fi
    else
        echo "Unspecified output -o or --output"
        echo "Using current dirrecory"
        OUT="."
    fi
    if [[ -n "$IN" ]]; then
        echo "[warning] argument ${IN} not needed with --old option"
    fi
fi
if [[ -n "$SCALE" ]]; then
    if ! [[ $SCALE =~ ^[0-9]+([.][0-9]+)?$ ]]; then
        echo "Bad -s --scale argument, must be unsigned double, type ptaskTracer --help for help"
        exit 1
    else
        echo "Printing scale set to ${SCALE}px/s"
    fi
else
    echo "Default printing scale set to 1000.0px/s"
    SCALE=1000.0
fi
if [[ -n "$HEIGHT" ]]; then
    if ! [[ $HEIGHT =~ ^[0-9]+([.][0-9]+)?$ ]]; then
        echo "Bad -h --theight argument, must be unsigned double, type ptaskTracer --help for help"
        exit 1
    else
        echo "Printing task height set to ${HEIGHT}px"
    fi
else
    echo "Default printing task height set to 20.0px"
    HEIGHT=20.0
fi
if [[ -n "$OS" ]]; then
    if ! [[ "$OS" = true ]]; then
    OS=false
    fi
else
    OS=false
fi
if [[ -n "$CPU" ]]; then
    if ! [[ "$CPU" = true ]]; then
        CPU=false
    fi
else
    CPU=false
fi
if ! [[ -f "/usr/local/lib/w3c.jar" ]]; then # checking jar java libs
    echo "Java jar lib w3c not found in /usr/local/lib, please run inslall.sh"
    exit 1
elif ! [[ -f "/usr/local/lib/antlr-4.7.2-complete.jar" ]]; then
    echo "Java jar lib antlr-4.7.2-complete not found in /usr/local/lib, please run install.sh"
    exit 1
elif ! [[ -f "/usr/local/lib/batik-1.10/lib/batik-all-1.10.jar" ]]; then
    echo "Java jar lib batik-all-1.10 not found in /usr/local/lib/batik-1.10/lib, please run install.sh"
    exit 1
elif ! [[ -f "/usr/local/lib/ptaskTracer-1.0.jar" ]]; then
    echo "Java jar lib ptaskTracer-1.0 not found in /usr/local/lib, please run install.sh"
    exit 1
fi
echo -e "[\033[0;32mOK\033[0m]"



if [[ -z "$OLD" ]]; then
    echo ""
    echo "> Configuring LTTng..."
    #configuring lttng
    if ! [[ -d ${OUT}/build ]]; then
        mkdir ${OUT}/build
    fi
    sudo rm -rf ${OUT}/build/lttng-traces/${APP}* 2 > /dev/null
    #sudo lttng-sessiond --daemonize
    sudo lttng create ${APP} --output=${OUT}/build/lttng-traces/${APP}
    if [ $? -eq 1 ]; then
        echo "lttng create error: tracing session ${APP} not created"
        exit 1
    fi
    sudo lttng enable-event --kernel --syscall clone
    if [ $? -eq 1 ]; then
        echo "lttng enable-event error: syscall clone not enabled"
        exit 1
    fi
    sudo lttng enable-event --kernel sched_switch
    if [ $? -eq 1 ]; then
        echo "lttng enable-event error: tracepoint sched_switch not enabled"
        exit 1
    fi
    sudo lttng enable-event --userspace ptask_provider:ptask_tracepoint
    if [ $? -eq 1 ]; then
        echo "lttng enable-event error: tracepoint ptask not enabled"
        exit 1
    fi
    #sudo lttng add-context --userspace --type=pthread_id --type=perf:thread:instructions
    sudo lttng add-context --kernel --type=pid --type=tid
    if [ $? -eq 1 ]; then
        echo "lttng add-context error: pid and tid not added"
        exit 1
    fi
    echo -e "[\033[0;32mOK\033[0m]"



    echo ""
    echo "> Starting tracing session..."
    #starting app
    sudo lttng start
    if [ $? -eq 1 ]; then
        echo "lttng start error: session not started"
    fi
    sudo sh -c "exec ./${APPPATH}/${APP} ${ARGS} > /dev/null &"
    if [ $? -eq 1 ]; then
        echo "Application $APP not started"
        exit 1
    fi
    PID=`pgrep ${APP}`
    echo "Starting the application ${APP}:${PID}"
    echo -e "[\033[0;32mOK\033[0m]"



    echo ""
    echo "> Waiting ${TIME}s..."
    #waiting
    sudo sleep $TIME
    echo -e "[\033[0;32mOK\033[0m]"



    echo ""
    echo "> Stoping tracing session..."
    #stoping the session
    sudo lttng stop
    if [ $? -eq 1 ]; then
        echo "Tracing session not stoped"
        exit 1
    fi
    sudo lttng destroy
    if [ $? -eq 1 ]; then
        echo "Tracing session not destoyed"
    fi
    sudo kill -9 `pgrep ${APP}`
    if [ $? -eq 1 ]; then
        echo "Application $APP not killed"
        exit 1
    fi
    echo "Killing the application ${APP}:${PID}"
    sleep .5
    echo -e "[\033[0;32mOK\033[0m]"
fi



#printing
if [[ -z "$OLD" ]]; then
    echo ""
    echo "> Parsing the trace..."
    sudo chown -R $(whoami) ${OUT}/build/lttng-traces/${APP}*
    if [[ "$OS" = true ]]; then
        sudo babeltrace ${OUT}/build/lttng-traces/${APP}* > ${OUT}/build/${APP}_raw.txt
        if [ $? -eq 1 ]; then
            echo "Trace cannot be read"
            exit 1
        fi
    else
        sudo babeltrace ${OUT}/build/lttng-traces/${APP}* | sed '/ptask_tracepoint\|exit_clone.*pid\ =\ '"${PID}"'\|exit_clone.*ret\ =\ '"${PID}"'\|sched_switch.*prev_comm\ =\ \"'"${APP}"'\".*next_comm\ =\ \"'"${APP}"'\"\|sched_switch.*pid\ =\ '"${PID}"'/!d' > ${OUT}/build/${APP}_raw.txt
        if [ $? -eq 1 ]; then
            echo "Trace cannot be read"
        fi
    fi
    echo "Raw trace saved in ${OUT}/build/${APP}_raw.txt"
else
    echo ""
    echo "> Parsing the trace..."
    echo "Using trace saved in ${OUT}/build/${APP}_raw.txt"
    if ! [[ -f "${OUT}/build/${APP}_raw.txt" ]]; then
        echo "File ${OUT}/build/${APP}_raw.txt not found"
        exit 1
    fi
    PID="0"
fi
#java -cp ./class:${CLASSPATH} com.main $APP $PID $SCALE $HEIGHT $OS $CPU
java -jar /usr/local/lib/ptaskTracer${VERSION}.jar $APP $PID $SCALE $HEIGHT $OS $CPU $OUT
RET=$?
if [ $RET -ne 0 ]; then # redoing for exit(2)
    if [ $RET -eq 1 ]; then
        echo "Parser/Printer cannot be open"
        exit 1
    elif [ $RET -eq 2 ]; then
        echo "For java.lang.OutOfMemoryError, reduce de size of ${OUT}/build/${APP}_raw.txt"
        echo "And redo the command with option --old"
        sudo chown -R $(whoami) ${OUT}/build/*
        exit 0
    fi
fi
sudo chown -R $(whoami) ${OUT}/build/*
exit 0
