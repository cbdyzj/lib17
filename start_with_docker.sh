cd `dirname $0`
# Use Docker Node.js image
docker run -d \
--name lib17 \
-v `pwd`/src:/opt/lib17/src:ro \
-v `pwd`/static:/opt/lib17/static:ro \
-v `pwd`/package.json:/opt/lib17/package.json:ro \
-v `pwd`/yarn.lock:/opt/lib17/yarn.lock:ro \
-w /opt/lib17 \
-p 127.0.0.1:3000:3000 \
--network=docker-network \
node:12 bash -c 'yarn install --ignore-optional && yarn start'
