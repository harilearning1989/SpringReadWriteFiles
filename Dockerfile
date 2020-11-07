FROM adoptopenjdk:11-jre-hotspot
VOLUME /tmp
EXPOSE 8081
ADD build/libs/spring-read-file.war spring-read-file.war
ENTRYPOINT ["java","-jar","spring-read-file.war"]