@echo off
echo [INFO] install into local repository.

cd %~dp0
cd ..
call mvn clean install -Dmaven.test.skip=true
cd bin
pause