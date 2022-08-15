FROM openjdk:8-jdk-alpine
MAINTAINER airRetailer
COPY flight_amenity_web/target/flight_amenity_web-1.0-SNAPSHOT-jar-with-dependencies.jar flight-amenity-1.0.jar
ENTRYPOINT ["java","-jar","/flight-amenity-1.0.jar"]