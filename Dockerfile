FROM  maven:3.9.6-eclipse-temurin-21 AS build
LABEL authors="temetski33"

WORKDIR /app

COPY pom.xml .

COPY . /app

RUN mvn clean package -DskipTests

CMD ["java", "-jar", "target/fuelcalculator.jar"]