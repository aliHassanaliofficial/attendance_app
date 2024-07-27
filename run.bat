@echo off
:: This batch file elevates the Java application
powershell Start-Process -FilePath "java.exe" -ArgumentList "-jar your_application.jar" -Verb RunAs
