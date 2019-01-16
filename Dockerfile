FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/search-0.0.1-SNAPSHOT.war app.war
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.war"]
