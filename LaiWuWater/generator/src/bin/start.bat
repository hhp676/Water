@echo off

set MAVEN_OPTS=%MAVEN_OPTS% -XX:MaxPermSize=128m
cd %~dp0
cd ../..
mvn clean jetty:run -Djetty.port=7777 

goto end
:end