# WebPostits_1
 Provide unconference marketplace/timetable, sourced from QiqoChat. 
 The project layout is as required by Elastic Beanstalk
 (https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/java-tomcat-platform-directorystructure.html). 
 
 build.sh creates the ROOT.war, which runs on Tomcat 8.5, and is what is uploaded to AWS. 

## Docker

The provided Dockerfile will perform a self-contained multi-stage build, so just build the image and you are good to go:
```
docker build -t jalba .
```

Afterwards you can run a container based on the created image (internal port is `8080`)
``` 
docker run -p 80:8080 jalba
```
and the tomcat server will be started with the deployed app available at [http://localhost/jalba/](http://localhost/jalba/).
