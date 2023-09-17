FROM openjdk:17-oracle as builder
LABEL authors="mkotlov789"
RUN mkdir -p /app/source
COPY . /app/source
WORKDIR /app/source
RUN ./mvnw clean package

FROM openjdk:17-oracle as runtime
LABEL authors="mkotlov789"
COPY --from=builder /app/source/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]