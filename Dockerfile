FROM openjdk:8-slim
COPY mobtravelapp/target/mobtravelapp-0.0.1-SNAPSHOT.jar /app/mobtravelapp.jar
WORKDIR /app
CMD ["java", "-jar", "mobtravelapp.jar"]
