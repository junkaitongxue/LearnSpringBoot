@echo off
pg_ctl -D D:\ProgramData\Pg -l logfile status | findstr pg_ctl | findstr PID
if "%errorlevel%"=="0" echo "pg服务器已经启动,开始关闭..."& pg_ctl -D "D:\ProgramData\Pg" -l logfile stop &goto end
echo 开始启动pg服务器...
pg_ctl -D "D:\ProgramData\Pg" -l logfile start

:end
pause