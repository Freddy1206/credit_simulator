# Gunakan base image untuk Maven
FROM maven:3.8.5-openjdk-8 AS builder

# Set working directory
WORKDIR /build

# Copy seluruh proyek ke dalam container
COPY . .

# Jalankan build Maven
RUN mvn clean package

# Gunakan base image untuk menjalankan aplikasi
FROM openjdk:8-jre-slim

# Set working directory
WORKDIR /app

# Salin file JAR hasil build dari tahap sebelumnya
COPY --from=builder /build/target/credit-simulator-application-1.0.0-jar-with-dependencies.jar /app/credit_simulator.jar

# Expose port
EXPOSE 8080

# Jalankan aplikasi
ENTRYPOINT ["java", "-jar", "/app/credit_simulator.jar"]
