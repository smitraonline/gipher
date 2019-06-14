FROM java:8-jre
WORKDIR usr/src
ENV MONGO_URI=mongodb://localhost:27017/gipherdb
ENV MONGO_DATABASENAME=gipherdb
ADD ./target/gipherrecommendersystem-1.0.jar /usr/src/gipherrecommendersystem-1.0.jar
ENTRYPOINT ["java","-jar","gipherrecommendersystem-1.0.jar"]