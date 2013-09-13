#!/bin/bash
INSTALLDIR="/home/nickapos/keimena/mybill"
CLASSPATH="$INSTALLDIR/lib"
cd $INSTALLDIR
/opt/jdk6//bin/java -cp $CLASSPATH -jar myBill.jar&
