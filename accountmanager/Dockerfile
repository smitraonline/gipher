FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_URI=jdbc:mysql://localhost:3306/userdb
ENV MYSQL_DATABASE=userdb
ENV MYSQL_USER=approot
ENV MYSQL_PASSWORD=root123
ADD ./target/accountmanager-1.0.jar /usr/src/accountmanager-1.0.jar
ENTRYPOINT ["java","-jar","accountmanager-1.0.jar"]