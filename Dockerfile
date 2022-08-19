FROM openjdk:11
MAINTAINER airRetailer
COPY flight_amenity_web/target/ar_flight-amenity.jar ar_flight-amenity.jar
ENTRYPOINT ["java","-jar","/ar_flight-amenity.jar"]