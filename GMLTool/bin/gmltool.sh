#!/bin/bash
# linux 32bit script for running GML Tool

#setup classpath
CLASSPATH=$CLASSPATH:../lib/GMLTool.jar
CLASSPATH=$CLASSPATH:../lib/worldwind.jar
CLASSPATH=$CLASSPATH:../lib/worldwindx.jar
CLASSPATH=$CLASSPATH:../lib/jogl.jar
CLASSPATH=$CLASSPATH:../lib/gluegen-rt.jar
CLASSPATH=$CLASSPATH:../lib/jgoodies-common-1.4.0.jar
CLASSPATH=$CLASSPATH:../lib/jgoodies-looks-2.5.2.jar

#launch jvm with natives in linux folder
java -Xms128m -Dsun.java2d.noddraw=true -Djava.library.path=../lib/linux -cp $CLASSPATH gmltool.App
