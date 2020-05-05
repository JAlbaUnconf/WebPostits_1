cd src

rm -rf WEB-INF/classes/
mkdir WEB-INF/classes

javac -d WEB-INF/classes -cp WEB-INF/lib/\* scot/jalba/*.java

jar -cvf ROOT.war *.jsp WEB-INF
mv ROOT.war ../
