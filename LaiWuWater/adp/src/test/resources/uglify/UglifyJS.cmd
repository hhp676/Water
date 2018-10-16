@echo off
REM =====================================
REM    UglifyJS
REM
REM     - by cx
REM     - 2012-05-03
REM =====================================
SETLOCAL ENABLEEXTENSIONS

echo.
echo UglifyJS 

REM 过滤文件后缀，只压缩 js 和 css
if "%~x1" NEQ ".js" (
    if "%~x1" NEQ ".css" (
        if "%~x1" NEQ ".jsp" (
            echo.
            echo **** 请选择 CSS、JS、JSP文件
            echo.
            goto End
        )
    )
)


REM 生成压缩后的文件名，规则为：
REM 1. 文件名有 .source 时: filename.source.js -> filename.js
REM 2. 其它情况：filename.js -> filename-min.js
set RESULT_FILE=%~n1-min%~x1
dir /b "%~f1" | find ".source." > nul
if %ERRORLEVEL% == 0 (
    for %%a in ("%~n1") do (
        set RESULT_FILE=%%~na%~x1
    )
)

REM 调用 uglifyjs 压缩文件

node "%~dp0\bin\uglifyjs" %~nx1 > %RESULT_FILE%



REM 显示压缩结果
if %ERRORLEVEL% == 0 (
    echo.
    echo 压缩文件 %~nx1 到 %RESULT_FILE% 
    for %%a in ("%RESULT_FILE%") do (
        echo 文件大小从 %~z1 bytes 压缩到 %%~za bytes
    )
    echo.
) else (
    echo.
    echo **** 文件 %~nx1 中有写法错误，请仔细检查
    echo.
	goto End
)
goto End


:End
ENDLOCAL
pause
