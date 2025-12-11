
FROM maven:3.9.5-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jre-alpine


WORKDIR /usr/app

COPY --from=builder /app/target/muebleria-0.0.1-SNAPSHOT.jar muebleria.jar

EXPOSE 8080


ENTRYPOINT ["java","-jar","muebleria.jar"]