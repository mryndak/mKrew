FROM openjdk:17
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} mkrew.jar
ENTRYPOINT ["java","-jar","/mkrew.jar"]