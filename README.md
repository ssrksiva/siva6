# Sample Microservice Project

1)Go to https://start.spring.io/

2)Enter project details

  Type of Project
  
  Language
  
  Spring Boot version
  
  group Id
  
  artifact Id
  
  Add Dependencies(web)

3)Create API Calls

  Add RestController
  
  RequestMapping
  
  Type Of call(Get or Post)
  

4)Add to pom.xml

            <plugin>
                <groupId>com.spotify</groupId>
                <artifactId>docker-maven-plugin</artifactId>
                <version>1.2.0</version>
                <configuration>
                    <imageName>${docker.image.prefix}/${project.artifactId}</imageName>
                    <imageTags>
                        <imageTag>${project.version}</imageTag>
                        <imageTag>latest</imageTag>
                    </imageTags>
                    <!-- optionally overwrite tags every time image is built with docker:build -->
                    <forceTags>true</forceTags>
                    <dockerDirectory>src/main/docker</dockerDirectory>
                    <buildArgs>
                        <JAR_FILE>${project.build.finalName}.jar</JAR_FILE>
                    </buildArgs>
                    <resources>
                        <resource>
                            <targetPath>/</targetPath>
                            <directory>${project.build.directory}</directory>
                            <include>${project.build.finalName}.jar</include>
                        </resource>
                    </resources>
                </configuration>
            </plugin>
	
5)create DockerFile 

   FROM openjdk:8-jdk-alpine
   
   VOLUME /tmp
   
   ADD target/demo3455-0.0.1-SNAPSHOT.jar demo3455-0.0.1-SNAPSHOT.jar
   
   ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","demo3455-0.0.1-SNAPSHOT.jar"]
	
6)cmd for Docker Build
			
   docker build -f DockerFile -t demo3455 .

7)cmd for Docker Run 

   docker run -p 8080:8080 demo3455
   
8)cmd for push

   docker tag demo3455 sssrkbsc/demo3455 (or $HOST/demo3455)
   
   docker push sssrkbsc/demo3455         (or $HOST/demo3455)
  
9)create kubernettes deployment and servuice definition file demo3455.yml

```
kind: Service
apiVersion: v1
metadata:
  name: demo3455
spec:
  type: NodePort
  selector:
    app: demo3455
  ports:
  - protocol: TCP
    port: 8080
  
---

apiVersion: apps/v1
kind: Deployment
metadata:
  name: demo3455
spec:
  selector:
      matchLabels:
        app: demo3455
  replicas: 1
  template:
    metadata:
      labels:
        app: demo3455
    spec:
      containers:
        - name: demo3455
          image: sssrkbsc/demo3455:latest
          ports:
            - containerPort: 8080  
```
  
10)Deploy in Kubernettes

kubectl create -f demo3455.yml


