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

   docker run -p 8080:8080 docker3455