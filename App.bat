@echo off
setlocal
cd /d "%~dp0"
where javac >nul 2>&1
if errorlevel 1 (
    echo JDK belum ditemukan. Instal JDK dan tambahkan folder bin ke PATH.
    pause
    exit /b 1
)
if not exist build mkdir build
javac -encoding UTF-8 -d build Menu.java Main.java
if errorlevel 1 (
    echo Kompilasi gagal. Periksa pesan di atas.
    pause
    exit /b 1
)
java -cp build Main
echo.
pause
