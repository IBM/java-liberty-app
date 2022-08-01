# Package the application as a war file
FROM registry.access.redhat.com/ubi8/openjdk-17:1.14 AS builder
LABEL maintainer="IBM Java Engineering at IBM Cloud"

USER root
WORKDIR /app
COPY pom.xml ./
COPY src src/
RUN mvn clean package
USER 1001

# Copy the war file over to the open liberty image
FROM openliberty/open-liberty:kernel-slim-java17-openj9-ubi

# disable vulnerable TLS algorithms
USER root
RUN sed -i 's/jdk.tls.disabledAlgorithms=/jdk.tls.disabledAlgorithms=SSLv2Hello, DES40_CBC, RC4_40, SSLv2, TLSv1, TLSv1.1, /g' /opt/java/openjdk/conf/security/java.security
USER 1001

COPY --from=builder --chown=1001:0 /app/src/main/liberty/config/ /config/
COPY --from=builder --chown=1001:0 /app/target/*.war /config/apps

RUN featureUtility installFeature microProfile-3.3

ENV PORT 9080

EXPOSE 9080

RUN configure.sh
