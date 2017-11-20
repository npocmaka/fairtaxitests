@echo off
call killchromes.bat
call mvn test -DrunSuite=**/PureSeleniumSuite.class
::call mvn test -DrunSuite=**/ImageUploadSuite.class
call killchromes.bat
for %%x in (%cmdcmdline%) do if /i "%%~x"=="/c" pause