set CLASSPATH=.;%RTM_JAVA_ROOT%\jar\OpenRTM-aist-1.0.0.jar;%RTM_JAVA_ROOT%\jar\commons-cli-1.1.jar
cd /d %~dp0
cd bin
java VirtualJoystickComp -f rtc.conf %*
pause;
