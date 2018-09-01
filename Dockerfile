FROM openjdk:8-alpine

COPY . /usr/src/cambihealth/
WORKDIR /usr/src/cambihealth/
CMD ["java", "-jar", "cambiahealth.jar"]