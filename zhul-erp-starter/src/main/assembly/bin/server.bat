@echo off
cd /d %~dp0

if "%1" == "start" (
    call start.bat
) else (
    if "%1" == "stop" (
        call stop.bat
    ) else (
        if "%1" == "debug" (
            call start.bat debug
        ) else (
            if "%1" == "restart" (
                call restart.bat
            ) else (
                if "%1" == "dump" (
                    call dump.bat
                ) else (
                    echo ERROR: Please input argument: start or stop or debug or restart or dump
                    exit /b 1
                )
            )
        )
    )
)
