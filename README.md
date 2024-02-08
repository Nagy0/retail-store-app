# retail-store-app
This is a spring boot app technical assessment for retail store.

Note: This app is implmented using java 17 with spring boot 3.2.2, also it supports OpenApi on http://localhost:8080/swagger-ui/index.html#/

Steps to import and start the project.

1. Clone/download the project then extract it.
2. import the project to intellij or any IDE.
3. Start the project as any normal spring boot app, it will start on port 8080.
4. You can find all the test cases under src/test/java under com.xische package.
5. if you are on intellij just right click on the project then click Run 'all tests' with coverage, it will generate a report for the covered lines, and as you will find it will be 100% for service and controller layers.


if you are not on intellij add the jacoco plugin to pom.xml then re-run all the tests it will generate jacoco report under target folder.

<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.7.7.201606060606</version>
    <executions>
        <execution>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>report</id>
            <phase>prepare-package</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>
