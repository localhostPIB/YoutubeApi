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
* [Apache PDFBox](https://pdfbox.apache.org)
* [OpenjFX](https://openjfx.io/)
* [MaterialFX](https://github.com/palexdev/MaterialFX/)

## Hint
The logos are from [Pixabay](https://pixabay.com/).
* [Icon MainFrame](https://pixabay.com/de/vectors/youtube-logo-grafik-rot-1837872/)
* [CD Icon](https://pixabay.com/de/vectors/cd-computer-scheibe-gerettet-1169624/)
* [Loadscreen](https://pixabay.com/de/photos/cms-wordpress-265128/)
* [Root-Screen](https://pixabay.com/de/vectors/film-kino-video-motion-picture-158157/)
* [VideoInfo Frame](https://pixabay.com/de/vectors/rahmen-schwarz-wei%c3%9f-film-1662287/)
* [Loading Frame](https://pixabay.com/de/photos/erde-internet-globalisierung-2254769/)
* [VideoInfo Icon](https://pixabay.com/de/vectors/videokassette-vhs-video-kassette-4010202/)

## System-Requirements
* ![Java 17](https://img.shields.io/badge/Java-17-green.svg) / ![](https://img.shields.io/badge/Java%20JDK-1.8-green)/ ![](https://img.shields.io/badge/Maven-3.8.4-green.svg)
* ![OSX](https://img.shields.io/badge/OS-OSX-green.svg) / ![Linux](https://img.shields.io/badge/OS-Linux-green.svg)/ ![Ubuntu](https://img.shields.io/badge/OS-Ubuntu-green.svg) /
  ![Windows](https://img.shields.io/badge/OS-Windows-green.svg)
