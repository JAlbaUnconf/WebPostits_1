cd src
javac -d WEB-INF/classes -cp WEB-INF/lib/\* scot/jalba/PostitsServlet.java

jar -cvf ROOT.war *.jsp *.yaml WEB-INF 
cp ROOT.war /users/mpn/Java/apache-tomcat-8.5.54/webapps
mv ROOT.war ../
