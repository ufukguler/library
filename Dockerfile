FROM openjdk:8-jdk-alpine
ADD ./target/library*.jar /usr/src/library.jar
WORKDIR /usr/src
ENTRYPOINT ["java","-jar", "library.jar"]
