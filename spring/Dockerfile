FROM registry.access.redhat.com/ubi8/openjdk-11-runtime

USER root

ARG VERSION=1.0.0
ARG REVISION=SNAPSHOT

LABEL \
  org.opencontainers.image.authors="aushacker" \
  org.opencontainers.image.vendor="github" \
  org.opencontainers.image.url="local" \
  org.opencontainers.image.source="https://github.com/aushacker/otel/spring" \
  org.opencontainers.image.version="$VERSION" \
  org.opencontainers.image.revision="$REVISION" \
  vendor="github" \
  name="otel example" \
  version="$VERSION-$REVISION" \
  summary="Sample Spring Boot for testing Opentelemetry instrumentation" \
  description="This image contains the example microservice running with the Spring Boot runtime."

RUN mkdir -p /opt/app

COPY target/spring-1.0.0-SNAPSHOT.jar opentelemetry-javaagent-1.19.0.jar /opt/app/

RUN chown -R 1001:0 /opt && \
    chmod -R g=u /opt

EXPOSE 8080

USER 1001

CMD ["java", "-javaagent:/opt/app/opentelemetry-javaagent-1.19.0.jar", "-jar", "/opt/app/spring-1.0.0-SNAPSHOT.jar"]
