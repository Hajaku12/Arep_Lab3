# Web Framework Development

## Introduction
In this project, we will enhance an existing web server by converting it into a fully functional web framework. The framework will support the development of web applications with backend REST services. The new framework will provide tools to define REST services using annotations, manage query values within requests, and specify the location of static files.

## Overview
In this project, we will enhance an existing web server by converting it into a fully functional web framework. The framework will support the development of web applications with backend REST services. The new framework will provide tools to define REST services using annotations, manage query values within requests, and specify the location of static files. Additionally, the project will focus on server architectures, object meta-protocols, the IoC pattern, and reflection in Java.

## Project Scope and Features

### 1. Server Architectures
Implement a web server capable of delivering HTML pages and PNG images. The server will handle multiple non-concurrent requests and provide a framework for building web applications from POJOs.

### 2. Object Meta-Protocols
Develop a mechanism to load and manage POJOs using Java's reflection capabilities. This will allow the framework to dynamically discover and instantiate components at runtime.

### 3. IoC Pattern
Introduce an Inversion of Control (IoC) framework to manage the lifecycle and dependencies of web application components. This will enable the automatic injection of dependencies into components.

### 4. Reflection
Utilize Java's reflection API to scan the classpath for annotated classes and methods. This will allow the framework to automatically register and map REST services based on annotations such as `@RestController`, `@GetMapping`, and `@PostMapping`.

### 5. GET and POST Methods for REST Services
Implement `@GetMapping` and `@PostMapping` annotations that allow developers to define REST services using methods in controller classes. This feature will enable developers to define simple and clear routes within their applications, mapping URLs to specific methods that handle the requests and responses.

### 6. Static File Location Specification
Introduce a mechanism that allows developers to define the folder where static files are located. The framework will then look for static files in the specified directory, making it easier for developers to organize and manage their application's static resources.
## Implementation

- Rest Services

   - `http://localhost:8080/hello?name=Hann`

      ![name.png](images%2Fname.png)

  - `http://localhost:8080/index.html`

      ![index.png](images%2Findex.png)
  
  -  Hello Service
  ```bash
    java -cp target/classes org.example.SpringECI org.example.HelloService 
    ``` 
  
   ![HelloService.png](images%2FHelloService.png)

   - GoodbyeService
    ```bash
        java -cp target/classes org.example.SpringECI org.example.GoodbyeService 
   ```
  ![GoodbyeService.png](images%2FGoodbyeService.png)
   - WelcomeService
    ```bash
        java -cp target/classes org.example.SpringECI org.example.WelcomeService 
    ```
  ![WelcomeService.png](images%2FWelcomeService.png)
- Static Files

  - `http://localhost:8080/example.html`

      ![examplehtml.png](images%2Fexamplehtml.png)

    - `http://localhost:8080/example.png`
  
    ![examplepng.png](images%2Fexamplepng.png)

  - `http://localhost:8080/example.jpg`
  
      ![examplejpg.png](images%2Fexamplejpg.png)

  - `http://localhost:8080/example.js`
  
    ![examplejs.png](images%2Fexamplejs.png)

  - `http://localhost:8080/example.txt`
  
      ![exampletxt.png](images%2Fexampletxt.png)

- Test 
   ```bash
        java -cp target/classes org.example.JUnitECi org.example.ClassToBeTested
   ```
   ![test.png](images%2Ftest.png)

## How to Run
1. **Clone the repository**:
   ```bash
   git clone https://github.com/Hajaku12/Arep_Lab3.git
   ```

2. **Compile and Run the Server**:
   ```bash
   mvn clean install
   java -cp target/classes org.example.MicroSpringBoot org.example.SpringECI
   ```

3. **Access the Web Server**:
    - Open a web browser and navigate to `http://localhost:8080/index.html`.
    - The server will serve files from the `src/main/resources` directory.

4. **Access REST Services**:
    - To access the REST services, use the following endpoints:
        - `http://localhost:8080/hello?name=YOURNAME`

5. **Stop the Server**:
    - Press `Ctrl + C` to stop the server.


## Dependencies
- **Maven**: The project uses Maven to manage dependencies and build the project.


## Configuration
- **Root Directory**: The server serves files from the `src/main/resources` directory.
- **Port**: The server operates on port `8080` by default.
- **Static Files**: The server serves static files from the `src/main/resources` directory.
- **REST Services**: The server provides REST services under the `/` path.
- **Query Parameters**: The server extracts query parameters from the request URL.
- **Service Mapping**: The server maps URLs to methods in controller classes for handling requests.

## Generating Project Documentation

1. **Generate the Site**
    - Run the following command to generate the site documentation:
      ```sh
      mvn site
      ```

2. **Add Javadoc Plugin for Documentation**
    - Add the Javadoc plugin to the `reporting` section of the `pom.xml`:
      ```xml
      <project>
        ...
        <reporting>
          <plugins>
            <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-javadoc-plugin</artifactId>
              <version>2.10.1</version>
              <configuration>
                ...
              </configuration>
            </plugin>
          </plugins>
        </reporting>
        ...
      </project>
      ```

    - To generate Javadoc as an independent element, add the plugin in the `build` section of the `pom.xml`:
      ```xml
      <project>
        ...
        <build>
          <plugins>
            <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-javadoc-plugin</artifactId>
              <version>2.10.1</version>
              <configuration>
                ...
              </configuration>
            </plugin>
          </plugins>
        </build>
        ...
      </project>
      ```

3. **Generate Javadoc Commands**
    - Use the following commands to generate Javadocs:
      ```sh
      mvn javadoc:javadoc
      mvn javadoc:jar
      mvn javadoc:aggregate
      mvn javadoc:aggregate-jar
      mvn javadoc:test-javadoc
      mvn javadoc:test-jar
      mvn javadoc:test-aggregate
      mvn javadoc:test-aggregate-jar
      ```

## License
This project is licensed under the MIT License - see the `LICENSE.txt` file for details.

## Author
Hann Jang
