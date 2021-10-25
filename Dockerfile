FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/library/src
COPY pom.xml /home/library
RUN mvn -f /home/library/pom.xml clean install -DskipTests

FROM adoptopenjdk/openjdk11:ubi
COPY --from=build /home/library/target/library-0.0.1-SNAPSHOT.jar /usr/local/bin/library.jar
ENTRYPOINT ["java","-jar","/usr/local/bin/library.jar"]