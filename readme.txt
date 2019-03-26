Please read this document carefully before using the EDUREKA VM.


Below are important points about this VM, please go through it without fail.



1) Hadoop and all other components are present in /usr/lib/


JDK: /usr/lib/jvm/jdk1.8.0_144
hadoop : /usr/lib/hadoop-2.8.1
sqoop: /usr/lib/sqoop-1.4.6.bin__hadoop-2.0.4-alpha
flume-ng : /usr/lib/apache-flume-1.7.0-bin
spark: /usr/lib/spark-2.1.1-bin-hadoop2.7
scala: /usr/lib/scala-2.12.2
zookeeper: /usr/lib/zookeeper-3.4.10
kafka: /usr/lib/kafka_2.12-0.11.0.0
zeppelin: /usr/lib/zeppelin-0.7.2-bin-all



2) The paths of all the components are set.

JDK: .bashrc
hadoop: .bashrc
spark: .bashrc
scala: .bashrc
sqoop: .bashrc
flume: .bashrc
kafka: .bashrc
zookeeper: .bashrc


3)Sqoop is already present in VM. MySQL connector is present in /usr/lib/sqoop-1.4.6.bin__hadoop-2.0.4-alpha/lib/ directory

4)SBT is already setup in VM in /home/edureka/.sbt

5) When you are trying to access HDFS, you may get “NameNode is in SafeMode”
Then go to terminal and give the command “hadoop dfsadmin -safemode leave “
Now go and check your HDFS.


6) When you are closing the VM, use the option "Save the machine state", so that
when you restart the VM you are at same place where you left and all your
daemons are running.


7) By Default Hadoop Daemons are already running, check by running "sudo jps" command. In case daemons are missing, run the below command.

	sudo service hadoop-master restart

To start/stop Hadoop Daemons:

	For start   : sudo service hadoop-master start
	For stop    : sudo service hadoop-master stop

8) To start spark daemons go to spark directory  and type "./sbin/start-all.sh"
   To stop spark daemons type "./sbin/stop-all.sh"
   To start spark shell type "spark-shell" 
 
9) To start Zookeeper Daemons go to kafka directory and type "bin/zookeeper-server-start.sh config/zookeeper.properties"

10) To start Zeppelin go to zeppelin directory and type "./bin/zeppelin-daemon.sh start"
    To stop "./bin/zeppelin-daemon.sh stop"
    To restart "./bin/zeppelin-daemon.sh restart"


11) Web UI

   Hadoop : localhost:50070
   Spark : localhost:8080
   Zeppelin: localhost:8089

12) Password for mysql in Edureka VM is: Edurekasql_123 
