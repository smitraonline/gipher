FROM java:8-jre
WORKDIR usr/src
ENV MONGO_URI=mongodb://localhost:27017/gipherdb
ENV MONGO_DATABASENAME=gipherdb
ADD ./target/giphermanager-1.0.jar /usr/src/giphermanager-1.0.jar
ENTRYPOINT ["java","-jar","giphermanager-1.0.jar"]