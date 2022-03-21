FROM gradle:7.4.1-jdk17 as build
WORKDIR /home/gradle/src
COPY --chown=gradle:gradle build.gradle settings.gradle ./
COPY --chown=gradle:gradle src ./src
RUN gradle build -x test

FROM openjdk:17.0.2-oraclelinux8
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/room-reservation-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 80
ENTRYPOINT ["java", "-jar", "/app/app.jar"]