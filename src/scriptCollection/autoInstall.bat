@echo off

set folderPath=C:\ToDo\Installation
set files=%folderPath%\*.exe

for %%f in (%files%) do (
    echo Installing %%~nf...
    start /wait "" "%%f" /S
    echo Installation of %%~nf completed
)

echo All installations completed.
pause
