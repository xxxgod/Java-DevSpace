@echo off
cd %~dp0
chcp 1251
COLOR 0A
if exist boot call:repack
goto:eof
:repack
cd .\bin\
if exist boot rmdir /s /q boot
md .\boot\
cd ..
xcopy  /E .\boot .\bin\boot\
copy .\bin\bootimg.exe  .\boot\
cd .\boot
bootimg.exe  --repack-bootimg
if exist ..\new_boot.img move ..\new_boot.img ..\new_boot_bak.img
move .\boot-new.img ..\new_boot.img
del bootimg.exe
cd  ..\bin\
xcopy  /E .\boot ..\boot

rmdir /S /Q boot
exit
goto:eof

