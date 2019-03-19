set HSQLDB_LIB=hsqldb.jar

"C:\Program Files\Java\jdk-11.0.2\bin\java.exe" -classpath %HSQLDB_LIB% org.hsqldb.Server -database.0 file:blog -port 9003

pause