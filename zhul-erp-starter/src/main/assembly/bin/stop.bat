@echo off
cd /d %~dp0
set "BIN_DIR=%CD%"
cd ..
set "DEPLOY_DIR=%CD%"
set "CONF_DIR=%DEPLOY_DIR%\conf"

for /f "tokens=2 delims==" %%i in ('findstr /r /c:"deploy.application.name" "%CONF_DIR%\deploy.properties" 2^>nul') do set "SERVER_NAME=%%i"
for /f "tokens=2 delims==" %%i in ('findstr /r /c:"deploy.application.port" "%CONF_DIR%\deploy.properties" 2^>nul') do set "SERVER_PORT=%%i"
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

REM Uncomment the following lines if you want to execute dump.sh
REM if "%1" neq "skip" (
REM     call "%BIN_DIR%\dump.sh"
REM )

echo Stopping the %SERVER_NAME% ...
for %%i in (%PIDS%) do (
    taskkill /F /PID %%i >nul 2>&1
)

set "COUNT=0"
:WAIT
echo .\c
ping -n 2 127.0.0.1 >nul
set /a COUNT+=1
for %%i in (%PIDS%) do (
    tasklist /fi "pid eq %%i" /fo list | find "Image Name" >nul && set /a COUNT=0
)
if %COUNT% gtr 0 goto :WAIT

echo OK!
echo PID: %PIDS%
