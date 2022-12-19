@echo off
::echo --编译vuepress代码
::npm run docs:build


echo --提交本地仓库
git add .

set /p con=输入更新日志：
git commit -m "%con%"

echo --Push到远程仓库
git push 

::echo --更新Page
::start https://gitee.com/xxxgod/javadoc/pages

exit