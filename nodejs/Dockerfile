FROM registry.access.redhat.com/ubi8/nodejs-16-minimal

RUN npm install express \
                @opentelemetry/api \
                @opentelemetry/sdk-node \
                @opentelemetry/auto-instrumentations-node \
                @opentelemetry/instrumentation-http \
                @opentelemetry/instrumentation-express \
                @opentelemetry/exporter-trace-otlp-http

COPY ./*.js ./package.json ${HOME}/ 

ENTRYPOINT ["npm"]
CMD ["run", "start"]
