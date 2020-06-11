@echo off
set copy_origin=%1%
set copy_target=%2%
set dir=C:\FullStack\javaworkspace2\backend-emart
for /f "delims=" %%a in ('dir /b/ad/s "%copy_origin%"') do (
	echo %%a
	copy /y "%%a\*.war" %copy_target%
)
pause