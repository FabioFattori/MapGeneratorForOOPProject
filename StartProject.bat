echo "Starting project for Windows..."

if "%OS%"=="Windows_NT" setlocal
gradlew compileJava && cd app/build/classes/java/main && java create.random.map.App


