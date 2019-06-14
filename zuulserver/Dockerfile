FROM java:8-jre
WORKDIR /usr/src
ADD ./target/zuulserver-1.0.jar /usr/src/zuulserver-1.0.jar
ENTRYPOINT ["java","-jar","zuulserver-1.0.jar"]