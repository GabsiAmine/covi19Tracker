FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/docker-spring-boot-cov19.jar docker-spring-boot-cov19.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","docker-spring-boot-cov19.jar"]