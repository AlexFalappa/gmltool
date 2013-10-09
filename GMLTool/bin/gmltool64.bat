rem Windows 64bit batch file for running GML Tool

rem setup classpath
set CLASSPATH=..\lib\GMLTool.jar
set CLASSPATH=%CLASSPATH%;..\lib\worldwind.jar
set CLASSPATH=%CLASSPATH%;..\lib\worldwindx.jar
set CLASSPATH=%CLASSPATH%;..\lib\jogl.jar
set CLASSPATH=%CLASSPATH%;..\lib\gluegen-rt.jar
set CLASSPATH=%CLASSPATH%;..\lib\jgoodies-common-1.4.0.jar
set CLASSPATH=%CLASSPATH%;..\lib\jgoodies-looks-2.5.2.jar

rem launch jvm with natives in windows folder
java -Xmx512m -Dsun.java2d.noddraw=true -Djava.library.path=..\lib\windows64 -cp %CLASSPATH% gmltool.App
