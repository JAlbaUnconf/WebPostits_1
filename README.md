# WebPostits_1
 Provide unconference marketplace/timetable, sourced from QiqoChat. 
 The project layout is as required by Elastic Beanstalk
 (https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/java-tomcat-platform-directorystructure.html). 
 
 build.sh creates the ROOT.war, which runs on Tomcat 7, and is what is uploaded to AWS. 

## Docker

To run the application locally in a dockerized tomcat, run build.sh to get the war file built, then run 
```
docker build -t jalba .
```
to build the image and with
``` 
docker run -p 80:8080 jalba
```
you can start the tomcat server with the deployed app available at [http://localhost/jalba/](http://localhost/jalba/)
