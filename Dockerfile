FROM maven:3.8.1-jdk-11-slim as builder
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY . ./
RUN mvn package -Dmaven.test.skip=true

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=builder /app/target/companycar-0.0.1-SNAPSHOT.jar /app/companycar.jar

EXPOSE 8080
CMD ["java", "-jar", "companycar.jar"]