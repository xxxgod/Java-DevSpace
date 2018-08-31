@echo off
cd %~dp0
COLOR 0A
::echo [InternetShortcut] >.\宅科技-极客论坛.url 
::echo URL="http://bbs.zecoki.com" >>.\宅科技-极客论坛.url
::echo [InternetShortcut] > %USERPROFILE%\desktop\宅科技-极客论坛.url 
::echo URL="http://bbs.zecoki.com" >> %USERPROFILE%\desktop\宅科技-极客论坛.url
.\bin\curl.exe -o .\宅科技-极客论坛.url http://www.zecoki.com/xfiles/宅科技-极客论坛.url
xcopy 宅科技-极客论坛.url %USERPROFILE%\desktop
if not exist readme.txt echo --拖入boot.img完成解压，拖入boot文件夹完成打包 > readme.txt && echo. >> readme.txt && echo                                                     --专业ROM开发论坛bbs.zecoki.com >> readme.txt
if exist boot.img call:unpack
goto:eof	
:unpack
if exist boot rmdir /s /q boot
md ".\boot"
copy .\bin\bootimg.exe  .\boot
copy boot.img  .\boot
cd .\boot
.\bootimg.exe  --unpack-bootimg
del boot.img boot-old.img bootimg.exe
exit
goto:eof