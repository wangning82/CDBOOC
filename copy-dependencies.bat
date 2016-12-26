@echo off
echo.
echo 导出项目依赖的JAR
echo.
rem pause
rem echo.

set MAVEN_OPTS=%MAVEN_OPTS% -Xms256m -Xmx512m -XX:PermSize=128m -XX:MaxPermSize=256m

call mvn dependency:copy-dependencies -DoutputDirectory=disk
pause