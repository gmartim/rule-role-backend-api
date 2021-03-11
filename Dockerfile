FROM maven

WORKDIR "/app"
ADD . /app
RUN mvn clean package install

ENTRYPOINT ["java", "-jar", "target/rule-role-backend-api-1.0-SNAPSHOT.jar"]