FROM node:6.10

EXPOSE 3000

WORKDIR /app

ADD index.js .
ADD package.json .

RUN npm install

ADD app.js .

CMD ["node", "index.js"]