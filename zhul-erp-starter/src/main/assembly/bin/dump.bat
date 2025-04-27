@echo off
cd /d %~dp0
set "BIN_DIR=%CD%"
cd ..
set "DEPLOY_DIR=%CD%"
set "CONF_DIR=%DEPLOY_DIR%\conf"

for /f "tokens=2 delims==" %%i in ('findstr /r /c:"deploy.application.name" "%CONF_DIR%\deploy.properties" 2^>nul') do set "SERVER_NAME=%%i"
for /f "tokens=2 delims==" %%i in ('findstr /r /c:"deploy.logs.file" "%CONF_DIR%\deploy.properties" 2^>nul') do set "LOGS_FILE=%%i"

if not defined SERVER_NAME (
    for /f "delims=" %%i in ('hostname') do set "SERVER_NAME=%%i"
)

set "PIDS="
for /f "tokens=2" %%i in ('tasklist /fi "imagename eq java.exe" /fo list ^| find "PID"') do set "PIDS=!PIDS! %%i"

if not defined PIDS (
    echo ERROR: The %SERVER_NAME% does not started!
    exit /b 1
)

set "LOGS_DIR="
if defined LOGS_FILE (
    set "LOGS_DIR=%LOGS_FILE:~0,-15%"
) else (
    set "LOGS_DIR=%DEPLOY_DIR%\logs"
)

if not exist "%LOGS_DIR%" mkdir "%LOGS_DIR%"
set "DUMP_DIR=%LOGS_DIR%\dump"
if not exist "%DUMP_DIR%" mkdir "%DUMP_DIR%"
set "DATE_DIR=%DUMP_DIR%\%date:~-4,4%%date:~-7,2%%date:~-10,2%%time:~0,2%%time:~3,2%%time:~6,2%"
if not exist "%DATE_DIR%" mkdir "%DATE_DIR%"

echo Dumping the %SERVER_NAME% ...
for %%i in (%PIDS%) do (
    jstack %%i > "%DATE_DIR%\jstack-%%i.dump" 2>&1
    echo .\c
    jinfo %%i > "%DATE_DIR%\jinfo-%%i.dump" 2>&1
    echo .\c
    jstat -gcutil %%i > "%DATE_DIR%\jstat-gcutil-%%i.dump" 2>&1
    echo .\c
    jstat -gccapacity %%i > "%DATE_DIR%\jstat-gccapacity-%%i.dump" 2>&1
    echo .\c
    jmap %%i > "%DATE_DIR%\jmap-%%i.dump" 2>&1
    echo .\c
    jmap -heap %%i > "%DATE_DIR%\jmap-heap-%%i.dump" 2>&1
    echo .\c
    jmap -histo %%i > "%DATE_DIR%\jmap-histo-%%i.dump" 2>&1
    echo .\c
    if exist "%SystemRoot%\System32\lsof.exe" (
        "%SystemRoot%\System32\lsof.exe" -p %%i > "%DATE_DIR%\lsof-%%i.dump"
        echo .\c
    )
)

if exist "%SystemRoot%\System32\netstat.exe" "%SystemRoot%\System32\netstat.exe" -an > "%DATE_DIR%\netstat.dump" 2>&1
echo .\c
if exist "%SystemRoot%\System32\iostat.exe" "%SystemRoot%\System32\iostat.exe" > "%DATE_DIR%\iostat.dump" 2>&1
echo .\c
if exist "%SystemRoot%\System32\mpstat.exe" "%SystemRoot%\System32\mpstat.exe" > "%DATE_DIR%\mpstat.dump" 2>&1
echo .\c
if exist "%SystemRoot%\System32\vmstat.exe" "%SystemRoot%\System32\vmstat.exe" > "%DATE_DIR%\vmstat.dump" 2>&1
echo .\c
if exist "%SystemRoot%\System32\free.exe" "%SystemRoot%\System32\free.exe" -t > "%DATE_DIR%\free.dump" 2>&1
echo .\c
if exist "%SystemRoot%\System32\sar.exe" "%SystemRoot%\System32\sar.exe" > "%DATE_DIR%\sar.dump" 2>&1
echo .\c
if exist "%SystemRoot%\System32\uptime.exe" "%SystemRoot%\System32\uptime.exe" > "%DATE_DIR%\uptime.dump" 2>&1

echo OK!
echo DUMP: %DATE_DIR%
