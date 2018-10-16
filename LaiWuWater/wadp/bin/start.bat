@echo off
echo [Pre-Requirement] Makesure install JDK 7.0+ and set the JAVA_HOME.
echo [Pre-Requirement] Makesure install Maven 3.0.3+ and set the PATH.
echo [Pre-Requirement] Makesure install hgcore module already.

set MAVEN_OPTS=%MAVEN_OPTS% -XX:MaxPermSize=128m

cd %~dp0
cd ..

echo [Step 1] Start projects.
call mvn clean jetty:run 
if errorlevel 1 goto error

echo [INFO] Please wait a moment. Then you can browse: "http://localhost:8080/hgdf-adp/".
goto end

:error
echo [ERROR] refresh error!
:end
pause