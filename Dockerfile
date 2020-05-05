FROM tomcat:8.5-alpine


COPY ROOT.war /usr/local/tomcat/webapps/jalba.war

EXPOSE 8080

CMD ["catalina.sh", "run"]