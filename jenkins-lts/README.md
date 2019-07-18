## Create docker images

to create:
```
docker build . -t jenkins/jenkins:01
to run:
```
docker run -d -e JENKINS_USER=admin -e JENKINS_PASS=password -p 8080:8080 jenkins/jenkins:01
to list:
```
docker ps 
docker images
to delete all containers including running:
```
docker rm -f $(docker ps -aq)
to remove image created:
```
docker rmi -f 0592b6e26acf

