# Package the application as a war file
FROM maven:3.6.3-ibmjava-8-alpine AS builder
COPY pom.xml ./
COPY src src/
RUN mvn clean package

# Copy the war file over to the open liberty image
FROM openliberty/open-liberty:kernel-java8-openj9-ubi

COPY --from=builder --chown=1001:0 src/main/liberty/config/ /config/
COPY --from=builder --chown=1001:0 target/*.war /config/apps/

RUN configure.sh