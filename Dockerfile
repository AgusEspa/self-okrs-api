FROM openjdk:11.0.12-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
#for Heroku enable:
ENV PORT=${PORT}
#for local testing enable:
#EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
