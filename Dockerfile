FROM openjdk:11
EXPOSE 8082
COPY target/*.jar /devops_project.jar
ENTRYPOINT ["java", "-jar", "/devops_project.jar"]
