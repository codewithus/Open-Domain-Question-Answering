javac -classpath ".;.\src;.\lib\*"  -d .\bin .\src\wiki_infobox\Main_con.java

pause
wsgen -s src -cp bin -d bin wiki_infobox.Main_Con

pause