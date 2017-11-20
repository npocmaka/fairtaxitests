@echo off
setlocal EnableDelayedExpansion
set count=0
for /f "usebackq tokens=* delims=" %%a in (`wmic process where "name='chrome.exe' and CommandLine like '%%--enable-automation%%'" get ProcessID /Format:value`) do (
	for /f "tokens=1,2 delims==" %%A in ("%%a") do (
		set /a count=count+1
		set "%%A[!count!]=%%B"
	) 
)
::echo %processid%
for /L %%# in (1;1;%count%) do (
	taskkill /pid !processId[%%#]! /f 1>nul 2>&1
)

taskkill /im chromed* /f >nul 2>&1
endlocal
::for %%x in (%cmdcmdline%) do if /i "%%~x"=="/c" pause