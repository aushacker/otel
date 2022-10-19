package com.github.aushacker.otel.openliberty.health;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Readiness
@ApplicationScoped
public class ReadinessCheck implements HealthCheck {

  private static final String READINESS_CHECK = "Basic readiness check";
  @Override
  public HealthCheckResponse call() {
    if (System.getProperty("wlp.server.name").equals("defaultServer")) {
      return HealthCheckResponse.up(READINESS_CHECK);
    }
    return HealthCheckResponse.down(READINESS_CHECK);
  }
}