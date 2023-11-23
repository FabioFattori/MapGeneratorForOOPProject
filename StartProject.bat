echo "Starting project for Windows..."

if "%OS%"=="Windows_NT" setlocal
gradlew build && cd app/build/libs && java -jar app.jar


