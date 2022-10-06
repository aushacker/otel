const { registerInstrumentations } = require("@opentelemetry/instrumentation");
const { getNodeAutoInstrumentations } = require("@opentelemetry/auto-instrumentations-node");
const { HttpInstrumentation } = require("@opentelemetry/instrumentation-http");
const { ExpressInstrumentation } = require("@opentelemetry/instrumentation-express");

const { Resource } = require("@opentelemetry/resources");
const { SemanticResourceAttributes } = require("@opentelemetry/semantic-conventions");

const { NodeTracerProvider } = require("@opentelemetry/sdk-trace-node");
const { OTLPTraceExporter } = require("@opentelemetry/exporter-trace-otlp-http");
const { BatchSpanProcessor } = require("@opentelemetry/sdk-trace-base");

// This registers all instrumentation packages
registerInstrumentations({
  instrumentations: [
    // Express instrumentation expects HTTP layer to be instrumented
    getNodeAutoInstrumentations(),
    new HttpInstrumentation(),
    new ExpressInstrumentation(),
  ],
});

const resource =
  Resource.default().merge(
    new Resource({
      [SemanticResourceAttributes.SERVICE_NAME]: process.env.SERVICE_NAME || "otel-nodejs",
      [SemanticResourceAttributes.SERVICE_VERSION]: process.env.SERVICE_VERSION || "undefined",
    })
  );

const provider = new NodeTracerProvider({
    resource: resource,
});

const exporter = new OTLPTraceExporter({
    // optional - url default value is http://localhost:4318/v1/traces
    url: process.env.OTLP_URL || "htttp://localhost:4318/v1/traces",
    // optional - collection of custom headers to be sent with each request, empty by default
    headers: {},
  });

const processor = new BatchSpanProcessor(exporter);
provider.addSpanProcessor(processor);

provider.register();
