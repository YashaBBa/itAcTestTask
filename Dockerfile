FROM maven:3.8.4-jdk-11 AS ModuleController
WORKDIR /app
COPY ModuleController /app
RUN mvn clean package

FROM maven:3.8.4-jdk-11 AS MdouleConfig
WORKDIR /app
COPY ModuleConfig /app
RUN mvn clean package

FROM maven:3.8.4-jdk-11 AS ModuleService
WORKDIR /app
COPY ModuleService /app
RUN mvn clean package


FROM openjdk:11-jre-alpine
WORKDIR /app
COPY --from=ModuleController /app/ModuleController/target/ModuleController-0.0.1-SNAPSHOT.jar .
COPY --from=ModuleConfig /app/ModuleConfig/target/ModuleConfig-0.0.1-SNAPSHOT.jar .
COPY --from=ModuleService /app/ModuleService/target/ModuleService-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "ModuleController-0.0.1-SNAPSHOT.jar"]