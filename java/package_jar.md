针对jar进行打包，要引入哪些依赖，排除掉哪些依赖，应该怎么用。

```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>3.2.4</version>
                <executions>
                    <execution>
                        <id>shade</id>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                        <!-- 打包时执行 -->
                        <phase>package</phase>
                    </execution>
                </executions>
                <configuration>
                    <artifactSet>
                        <!-- 排除odps-sdk-core等依赖 -->
                        <excludes>
                            <exclude>com.aliyun.odps:*</exclude>
                        </excludes>
                        <!-- 包含fastjson2依赖 -->
<!--                        <includes>-->
<!--                            <include>com.alibaba:*</include>-->
<!--                            <include>com.alibaba.fastjson:*</include>-->
<!--                            <include>com.alibaba.fastjson2:*</include>-->
<!--                        </includes>-->
                    </artifactSet>
                </configuration>
            </plugin>
        </plugins>
    </build>
```
