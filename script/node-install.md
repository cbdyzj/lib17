# Node.js

```sh
#!/bin/sh
# node-v8.11.4
wget https://nodejs.org/dist/v8.11.4/node-v8.11.4-linux-x64.tar.xz -P ~/Downloads;
tar -xJf ~/Downloads/node-v8.11.4-linux-x64.tar.xz -C /opt;
echo 'PATH=$PATH:/opt/node-v8.11.4-linux-x64/bin' >> /etc/profile;
```
