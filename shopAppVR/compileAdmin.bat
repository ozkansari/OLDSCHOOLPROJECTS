@ECHO OFF
cd source
set CLASSPATH=
javac -classpath . AdminMainJFrame.java
java -Djava.security.policy=java.policy -classpath . AdminMainJFrame