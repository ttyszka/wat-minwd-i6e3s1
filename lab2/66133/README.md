# Otomoto.pl Scraper

Application uses the jsoup library. Based on the parameters provided by the user, the initial URL is generated and then all ads are being scraped. Then proceeds to the next page until all ads are scraped.

## Getting started:

To start the application from the command line, make sure you are inside the project with the solution. Prepare the jar using maven command:

```
mvn clean package
```

And then just simply run the command from the powershell/cmd: 
```
java -jar target/otomoto-scraper-task-2-1.0.jar
```

Note that you should have to [install JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and [Maven](https://maven.apache.org/install.html) as prerequisite.