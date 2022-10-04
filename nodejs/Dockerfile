FROM registry.access.redhat.com/ubi8/nodejs-16-minimal

RUN npm install express @opentelemetry/sdk-node @opentelemetry/api @opentelemetry/auto-instrumentations-node

COPY ./app.js ./tracing.js ./package.json ${HOME}/ 

ENTRYPOINT ["npm"]
CMD ["run", "start"]
