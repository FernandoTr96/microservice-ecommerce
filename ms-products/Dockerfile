# ETAPA 1: BUILD
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# ETAPA 2: RUNTIME
FROM eclipse-temurin:21-jre-alpine AS runtime
WORKDIR /app
COPY --from=builder /app/target/*.jar ms-products.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ms-products.jar"]
