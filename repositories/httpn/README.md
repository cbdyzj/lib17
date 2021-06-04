# httpn

## Using

- TypeScript
- Sequelize
- Koa.js
- Moment.js
- RabbitMQ

## Usage

```sh
npm install
npm start
```

## Up MySQL

```sh
docker-compose -f docker/mysql/docker-compose.yml up -d
mysql -uroot -p -h127.0.0.1 -P3306 < schema/schema.sql
```