const { HttpInstrumentation } = require("@opentelemetry/instrumentation-http");
const { ExpressInstrumentation } = require("@opentelemetry/instrumentation-express");
const opentelemetry = require("@opentelemetry/api");
const { Resource } = require("@opentelemetry/resources");
const { SemanticResourceAttributes } = require("@opentelemetry/semantic-conventions");
const { NodeTracerProvider } = require("@opentelemetry/sdk-trace-node");
const { registerInstrumentations } = require("@opentelemetry/instrumentation");
const { ConsoleSpanExporter, BatchSpanProcessor } = require("@opentelemetry/sdk-trace-base");

const otelsdk = require("@opentelemetry/sdk-node");
const {
  getNodeAutoInstrumentations,
} = require("@opentelemetry/auto-instrumentations-node");
const {
  OTLPTraceExporter,
} = require("@opentelemetry/exporter-trace-otlp-http");

const sdk = new otelsdk.NodeSDK({
  traceExporter: new OTLPTraceExporter({
    // optional - url default value is http://localhost:4318/v1/traces
    url: process.env.OTLP_URL || "localhost:4318/v1/traces",
    // optional - collection of custom headers to be sent with each request, empty by default
    headers: {},
  }),
  instrumentations: [getNodeAutoInstrumentations()],
});
sdk.start();

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
      [SemanticResourceAttributes.SERVICE_NAME]: "otel-nodejs",
      [SemanticResourceAttributes.SERVICE_VERSION]: "0.1.0",
    })
  );

const provider = new NodeTracerProvider({
    resource: resource,
});
const exporter = new ConsoleSpanExporter();
const processor = new BatchSpanProcessor(exporter);
provider.addSpanProcessor(processor);

provider.register();
