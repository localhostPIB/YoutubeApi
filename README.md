# Youtube Comment Collector

## Getting Started
### CLI
To use this program, you need a client secret. For more information, visit [Google Docs](https://developers.google.com/adwords/api/docs/guides/authentication).
The Main method class Starter.java starts the application, as arguments the client secret and video-id (e.g. https://www.youtube.com/watch?v={video-id}) are needed.
Command: `java -jar YoutubeApi-1.0-SNAPSHOT-jar-with-dependencies.jar videoId Client-Secret`

### JavaFX GUI
In the IDE you have to pass the following arguments `--module-path "{/path/toDownloaded/javafx-sdk-17.0.1/lib}" --add-modules javafx.controls, javafx.web, javafx.fxml`.

## Built With
For this we use the build management tool Maven(v. 3.8.4) from the Apache Software Foundation.
* [Maven](https://maven.apache.org/)

## Requirements
To start the "project" the frameworks and libraries listed below are required, which are provided in the `pom.xml` and
can be downloaded (from Maven) before starting the program. 
* [Hibernate](https://hibernate.org/)
* [Lombok](https://projectlombok.org/)
* [sqlite-dialect](https://github.com/gwenn/sqlite-dialect)
* [SQLite](https://www.sqlite.org/)
* [SQLite-JDBC](https://github.com/xerial/sqlite-jdbc)
* [Data Mapper For Jackson](https://mvnrepository.com/artifact/org.codehaus.jackson/jackson-mapper-asl)
* [OAuth 2.0 for Google-API](https://developers.google.com/identity/protocols/oauth2)
* [YouTube Data V3 support](https://developers.google.com/resources/api-libraries/documentation/youtube/v3/java/latest/com/google/api/services/youtube/YouTube.html)
* [Apache Commons CSV](https://commons.apache.org/proper/commons-csv/)
* [OpenjFX](https://openjfx.io/)

## System-Requirements
* ![Java 8](https://img.shields.io/badge/Java-8-green.svg) / ![](https://img.shields.io/badge/Java%20JDK-1.8-green)/ ![](https://img.shields.io/badge/Maven-3.8.4-green.svg)
* ![OSX](https://img.shields.io/badge/OS-OSX-green.svg) / ![Linux](https://img.shields.io/badge/OS-Linux-green.svg) /
  ![Windows](https://img.shields.io/badge/OS-Windows-green.svg)
