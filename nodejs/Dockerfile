FROM registry.access.redhat.com/ubi8/nodejs-16-minimal

RUN npm install express

COPY ./app.js ./package.json ${HOME}/ 

ENTRYPOINT ["npm"]
CMD ["run", "start"]
