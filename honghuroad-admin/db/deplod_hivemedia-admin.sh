#usage:
#sudo vim /etc/crontab
#/sbin/service crond restart

#!/bin/sh
source /etc/profile
serviceFile="honghuroad-admin.jar"
serviceBinPath="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd $serviceBinPath
cd ..
servicePath="$(pwd)"
javaParam=""
case "$1" in
start)
        echo "service:$serviceFile"
        count=`ps aux |  grep "$serviceFile" |grep -v -c grep`
        if [ $count -le 0 ]; then
        echo "启动..."
        nohup java -XX:+UseConcMarkSweepGC $javaParam -jar $servicePath/$serviceFile  >> $servicePath/logs/catalina.out 2>&1 &
        echo "启动服务，进程ID:$!"
        else
                echo "服务已经启动"
                exit 0
        fi
;;

stop)
        echo "服务:$serviceFile"
        echo "服务停止..."
        count=`ps aux |  grep "$serviceFile" |grep -v -c grep`
        if [ $count -gt 0 ]; then
                kill -9 `ps -ef|grep "$serviceFile"|grep -v grep|awk '{print $2}'`
                echo "杀死进程$serviceFile完成"
        fi
        echo "服务停止..."
;;

restart)
        shellName="$(basename "$0")";
        echo "service restart..."
        $serviceBinPath/$shellName stop
        sleep 1
        $serviceBinPath/$shellName start
;;

*)
echo "Usage: (start|stop|restart)"
esac

exit 0
#-------------------------------------------------------------------------------
