FROM openjdk

WORKDIR /app

COPY . /app

COPY mysql-connector-java-8.0.15.jar /app/lib/

RUN javac *.java

CMD ["java", "-cp","/app/lib/mysql-connector-java-8.0.15.jar:.","StudentSQL"]
