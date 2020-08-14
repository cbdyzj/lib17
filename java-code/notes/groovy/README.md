# Groovy

Java 9起移除了Java EE，需要额外添加module

```sh
export JAVA_OPTS='--add-modules java.xml.bind'
# 所有module
export JAVA_OPTS='--add-modules=ALL-SYSTEM'
```
