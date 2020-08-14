# Maven

## dependency

```xml
<build>
    <plugins>
        <plugin>  
            <groupId>org.apache.maven.plugins</groupId>  
            <artifactId>maven-dependency-plugin</artifactId>  
            <version>3.1.0</version>  
            <executions>  
                <execution>  
                    <id>copy-dependencies</id>  
                    <phase>package</phase>  
                    <goals>  
                        <goal>copy-dependencies</goal>  
                    </goals>  
                    <configuration>  
                        <outputDirectory>  
                            ${project.build.directory}/lib/  
                        </outputDirectory>  
                    </configuration>  
                </execution>  
            </executions>  
        </plugin>
    </plugins>  
</build>
```

## shade

- 如果使用Spring框架需要额外处理

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>3.1.0</version>
            <configuration>
                <transformers>
                    <transformer
                            implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                        <mainClass>org.jianzhao.Application</mainClass>
                    </transformer>
                </transformers>
            </configuration>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>shade</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

## webjar

```xml
<build>
    <resources>
        <resource>
            <excludes>
                <exclude>pom.xml</exclude>
            </excludes>
            <directory>${project.basedir}</directory>
            <targetPath>${project.build.outputDirectory}/META-INF/resources/webjars</targetPath>
        </resource>
    </resources>
</build>
```