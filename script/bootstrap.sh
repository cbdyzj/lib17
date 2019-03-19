#!/bin/sh

WORKDIR='.'
EXEUTOR='java -jar'
ENTRY='build/libs/boot-0.0.1.jar'


cd `dirname $0`
BOOTSTRAP=`pwd`'/'`basename $0`
cd $WORKDIR

start() {
    echo $$ > process.pid
    echo 'Process: '$$
    echo 'Arguments: '$@
    exec $EXEUTOR $ENTRY $@
}

startd() {
    echo 'Starting...'
    echo 'Arguments: '$@
    nohup $BOOTSTRAP start $@ >/dev/null 2>&1 &
}

stop() {
    if [ ! -f 'process.pid' ]; then
        echo 'File not exist: process.pid '
        return
    fi
    PID=`cat process.pid | awk '{print $1}'`
    echo 'Stop process: '$PID
    kill $PID
    while true; do
        PID_EXIST=`ps -fp $PID | grep $PID`
        if [ ! -n "$PID_EXIST" ]; then
            break
        fi
        sleep 1
        echo -e '.\c'
    done
    echo
    echo 'Process stoped: '$PID
    rm process.pid
}

bootstrap() {
    CMD=$1
    shift
    case $CMD in
        'stop')
            stop
        ;;
        'start')
            start $@
        ;;
        'restart')
            stop && start $@
        ;;
        'startd')
            startd $@
        ;;
        'restartd')
            stop && startd $@
        ;;
        *)
            echo 'Illegal command!'
        ;;
    esac
}

bootstrap $@
