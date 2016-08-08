@echo off
title Compiler
:build
cls
"C:\Program Files\Java\jdk1.6.0_25\bin\javac.exe" *.java
pause
goto :build