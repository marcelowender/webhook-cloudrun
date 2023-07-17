FROM maven:3.9.3-eclipse-temurin-17 AS build-env

WORKDIR /app
COPY pom.xml ./
COPY src ./src

RUN mvn package -DskipTests

FROM eclipse-temurin:17-jdk
COPY --from=build-env /app/target/webhook-cloudrun-*.jar /webhook-cloudrun.jar
CMD ["java", "-jar", "/webhook-cloudrun.jar"]
