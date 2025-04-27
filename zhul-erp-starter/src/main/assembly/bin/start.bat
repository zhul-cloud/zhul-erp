@echo off
cd /d %~dp0
set "BIN_DIR=%CD%"
cd ..
set "DEPLOY_DIR=%CD%"
set "CONF_DIR=%DEPLOY_DIR%\conf"

for /f "tokens=2 delims==" %%i in ('findstr /r /c:"deploy.application.name" "%CONF_DIR%\deploy.properties" 2^>nul') do set "SERVER_NAME=%%i"
for /f "tokens=2 delims==" %%i in ('findstr /r /c:"deploy.application.launch" "%CONF_DIR%\deploy.properties" 2^>nul') do set "SERVER_LAUNCH=%%i"
for /f "tokens=2 delims==" %%i in ('findstr /r /c:"deploy.application.port" "%CONF_DIR%\deploy.properties" 2^>nul') do set "SERVER_PORT=%%i"
for /f "tokens=2 delims==" %%i in ('findstr /r /c:"deploy.profiles.active" "%CONF_DIR%\deploy.properties" 2^>nul') do set "PROFILES_ACTIVE=%%i"
for /f "tokens=2 delims==" %%i in ('findstr /r /c:"deploy.jar.path" "%CONF_DIR%\deploy.properties" 2^>nul') do set "JAR_PATH=%%i"
for /f "tokens=2 delims==" %%i in ('findstr /r /c:"deploy.jar.name" "%CONF_DIR%\deploy.properties" 2^>nul') do set "JAR_NAME=%%i"
for /f "tokens=2 delims==" %%i in ('findstr /r /c:"deploy.logs.file" "%CONF_DIR%\deploy.properties" 2^>nul') do set "LOGS_FILE=%%i"

if not defined SERVER_NAME set SERVER_NAME=%COMPUTERNAME%

for /f "tokens=2 delims==" %%i in ('tasklist /fi "imagename eq java.exe" /fo list ^| find "PID"') do set "PIDS=%%i"
if defined PIDS (
    echo ERROR: The %SERVER_NAME% already started!
    echo PID: %PIDS%
    exit /b 1
)

if not defined LOGS_FILE set "LOGS_DIR=%DEPLOY_DIR%\logs"
if not exist "%LOGS_DIR%" mkdir "%LOGS_DIR%"
set "STDOUT_FILE=%LOGS_DIR%\stdout.log"

set "LIB_DIR=%DEPLOY_DIR%\lib"
set "LIB_JARS="
for %%i in ("%LIB_DIR%\*.jar") do set "LIB_JARS=!LIB_JARS!;%LIB_DIR%\%%~nxi"

set "JAVA_OPTS= -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true "
set "JAVA_DEBUG_OPTS="
if "%1"=="debug" set "JAVA_DEBUG_OPTS= -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n "
set "JAVA_JMX_OPTS="
if "%1"=="jmx" set "JAVA_JMX_OPTS= -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "
set "JAVA_MEM_OPTS="
java -version 2>&1 | findstr /i "64-bit" >nul
if %errorlevel% equ 0 (
    set "JAVA_MEM_OPTS= -server -Xmx512m -Xms512m -Xmn256m -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 "
) else (
    set "JAVA_MEM_OPTS= -server -Xms1g -Xmx1g -XX:SurvivorRatio=2 -XX:+UseParallelGC "
)

echo Starting the %SERVER_NAME% ...
start java %JAVA_OPTS% %JAVA_MEM_OPTS% %JAVA_DEBUG_OPTS% %JAVA_JMX_OPTS% -classpath "%CONF_DIR%;%LIB_JARS%" -jar %SERVER_LAUNCH% --server.port=%SERVER_PORT% --spring.application.name=%SERVER_NAME% --spring.profiles.active=%PROFILES_ACTIVE% > %STDOUT_FILE% 2>&1

set "COUNT=0"
:WAIT
ping -n 2 127.0.0.1 >nul
set /a COUNT+=1
tasklist /fi "imagename eq java.exe" /fo list | find "PID" >nul && goto :RUNNING
goto :WAIT

:RUNNING
echo OK!
tasklist /fi "imagename eq java.exe" /fo list | find "PID"
echo STDOUT: %STDOUT_FILE%
