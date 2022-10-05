FROM registry.access.redhat.com/ubi8/nodejs-16-minimal

RUN npm install express \
                @opentelemetry/api \
                @opentelemetry/sdk-node \
                @opentelemetry/auto-instrumentations-node \
                @opentelemetry/instrumentation-http \
                @opentelemetry/instrumentation-express \
                @opentelemetry/exporter-trace-otlp-http && \
    chown 1001:0 ${home}/.npm && \
    chmod g+w ${HOME}/.npm

COPY ./*.js ./package.json ${HOME}/ 

ENTRYPOINT ["npm"]
CMD ["run", "start"]
