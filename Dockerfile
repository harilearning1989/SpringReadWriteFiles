FROM adoptopenjdk:11-jre-hotspot
EXPOSE 8081
ADD build/libs/spring-rw-file.war spring-rw-file.war
ENTRYPOINT ["java","-jar","spring-rw-file.war"]