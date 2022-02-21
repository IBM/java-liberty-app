# Package the application as a war file
FROM registry.access.redhat.com/ubi8/openjdk-17:1.11 AS builder
LABEL maintainer="IBM Java Engineering at IBM Cloud"

WORKDIR /app
COPY pom.xml ./
COPY src src/
RUN mvn clean package

# Copy the war file over to the open liberty image
FROM openliberty/open-liberty:kernel-slim-java17-openj9-ubi

COPY --from=builder --chown=1001:0 /app/src/main/liberty/config/ /config/
COPY --from=builder --chown=1001:0 /app/target/*.war /config/apps

RUN featureUtility installFeature microProfile-3.3

ENV PORT 9080

EXPOSE 9080

RUN configure.sh
