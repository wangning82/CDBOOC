@echo off
echo.
echo install lost jars
echo.

set MAVEN_OPTS=%MAVEN_OPTS% -Xms256m -Xmx512m -XX:PermSize=128m -XX:MaxPermSize=256m

call mvn install:install-file -Dfile=beautyeye_lnf.jar -DgroupId=org.beautyeye -DartifactId=beautyeye -Dversion=1.0 -Dpackaging=jar
call mvn install:install-file -Dfile=Draw9Patch4j.jar -DgroupId=org.Draw9Patch4j -DartifactId=Draw9Patch4j -Dversion=1.0 -Dpackaging=jar
call mvn install:install-file -Dfile=ninepatch4j.jar -DgroupId=org.ninepatch4j -DartifactId=ninepatch4j -Dversion=1.0 -Dpackaging=jar
pause