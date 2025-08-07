@echo off
set DIR=%~dp0
set APP_HOME=%DIR%gradle\wrapper

"%JAVA_HOME%\bin\java" -cp "%APP_HOME%\gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain %*