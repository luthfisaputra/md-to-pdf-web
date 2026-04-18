# Tahap 1: Build aplikasi pakai Maven dan Java
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Tahap 2: Jalankan aplikasi
FROM eclipse-temurin:17-jre
WORKDIR /app
# Ngambil hasil build dari tahap 1
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]