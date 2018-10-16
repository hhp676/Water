@echo off
echo [Pre-Requirement] Makesure install JDK 7.0+ and set the JAVA_HOME.
echo [Pre-Requirement] Makesure install Maven 3.0.3+ and set the PATH.

cd %~dp0
cd ..

echo [INFO] refresh schema and data for project.
call mvn flyway:clean flyway:init flyway:migrate
if errorlevel 1 goto error

echo [INFO] refresh success!
goto end

:error
echo [ERROR] refresh error!
:end
pause