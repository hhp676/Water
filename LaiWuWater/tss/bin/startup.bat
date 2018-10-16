@echo off
if "%OS%" == "Windows_NT" setlocal
java -Xms128m -Xmx512m -jar ../lib/hgdf-tss-1.0.0-snapshot.jar
goto end
:end