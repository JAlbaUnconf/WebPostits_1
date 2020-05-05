cd src
javac -d WEB-INF/classes -cp WEB-INF/lib/\* scot/jalba/PostitsServlet.java

jar -cvf ROOT.war *.jsp *.yaml WEB-INF 
mv ROOT.war ../
