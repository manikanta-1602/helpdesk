# Stage 1: Build the Maven WAR
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B || true

COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run inside Apache Tomcat 9
FROM tomcat:9.0-jre17-temurin-jammy
WORKDIR /usr/local/tomcat

# Remove default webapps
RUN rm -rf webapps/*

# Deploy application as ROOT and renew
COPY --from=builder /app/target/renew-*.war webapps/ROOT.war
COPY --from=builder /app/target/renew-*.war webapps/renew.war

# Ensure directory for embedded database exists
RUN mkdir -p /usr/local/tomcat/data

# Render provides PORT environment variable (typically 10000)
ENV PORT=8080
EXPOSE 8080 10000

# Dynamically set Tomcat HTTP port to $PORT on start
CMD sed -i "s/port=\"8080\"/port=\"${PORT:-8080}\"/g" conf/server.xml && catalina.sh run
