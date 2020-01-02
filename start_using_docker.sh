cd `dirname $0`

docker run --name lib17 -v ./src:/opt/lib17/src:ro -v ./package.json:/opt/lib17/package.json:ro -w /opt/lib17 -p 127.0.0.1:3000:3000 --network=docker-network node:12 bash -c 'yarn install --ignore-optional && yarn start'
