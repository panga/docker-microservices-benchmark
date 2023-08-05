FROM node:20-alpine

EXPOSE 3000

WORKDIR /app

ADD package.json .

RUN npm install

ADD index.js .
ADD app.js .
ADD worker.js .

CMD ["node", "index.js"]