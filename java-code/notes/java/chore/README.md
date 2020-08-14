# Chore

## Gradle

- gradle wrapper：`gradle wrapper --gradle-version=5.0.0 --distribution-type=all`
- Distributions：https://services.gradle.org/

## Maven

- maven plugin：war, compiler, javadoc, source
- maven  wrapper：`mvn -N  io.takari:maven:wrapper`
- install local jar ：`mvn install:install-file`
- deploy local jar：`mvn deploy:deploy-file`
- skip enforcer: `mvn compile -Denforcer.skip=true`
- skip tests:
  - `mvn compile -DskipTests`
  - `mvn compile -Dmaven.test.skip=true`
  
### Properties

- failOnMissingWebXml
- maven.compiler.source
- maven.compiler.target
- encoding：project.build.sourceEncoding