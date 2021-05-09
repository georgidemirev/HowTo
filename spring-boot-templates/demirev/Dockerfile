#FROM openjdk:12
#COPY ./target/demirev-0.0.1-SNAPSHOT.jar /usr/src
#WORKDIR /usr/src
#RUN sh -c 'touch demirev-0.0.1-SNAPSHOT.jar'
#ENTRYPOINT ["java","-jar","demirev-0.0.1-SNAPSHOT.jar"]


FROM openjdk:12 as build
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN chmod +x ./mvnw
# download the dependency if needed or if the pom file is changed
RUN ./mvnw dependency:go-offline -B
COPY src src
RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Production Stage for Spring boot application image
FROM openjdk:12 as production
ARG DEPENDENCY=/app/target/dependency

# Copy the dependency application file from build stage artifact
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Run the Spring boot application
ENTRYPOINT ["java", "-cp", "app:/app/lib/*","DemirevApplication"]
