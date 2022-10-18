FROM icr.io/appcafe/open-liberty:full-java11-openj9-ubi

ARG VERSION=1.0
ARG REVISION=SNAPSHOT

LABEL \
  org.opencontainers.image.authors="aushacker" \
  org.opencontainers.image.vendor="github" \
  org.opencontainers.image.url="local" \
  org.opencontainers.image.source="https://github.com/aushacker/otel/openliberty" \
  org.opencontainers.image.version="$VERSION" \
  org.opencontainers.image.revision="$REVISION" \
  vendor="github" \
  name="otel example" \
  version="$VERSION-$REVISION" \
  summary="Sample OpenLiberty app for testing Opentelemetry instrumentation" \
  description="This image contains the example microservice running with the Open Liberty runtime."

COPY --chown=1001:0 src/main/liberty/config/ /config/
COPY --chown=1001:0 target/*.war /config/apps/

RUN configure.sh
