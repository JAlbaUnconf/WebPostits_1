cd src
rm -rf WEB-INF/classes/*
javac -d WEB-INF/classes -cp WEB-INF/lib/\* scot/jalba/*.java
jar -cvf ROOT.war *.jsp WEB-INF style
mv ROOT.war ../