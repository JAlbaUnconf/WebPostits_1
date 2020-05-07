FROM maven:3.6.3-jdk-8-slim as build

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml war:war

FROM tomcat:8.5-alpine

COPY --from=build /usr/src/app/ROOT.war /usr/local/tomcat/webapps/jalba.war

EXPOSE 8080

CMD ["catalina.sh", "run"]