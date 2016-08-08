@echo off
title Compiler
:build
cls
"C:\Program Files\Java\jdk1.8.0_40\bin\javac.exe" *.java
pause
goto :build