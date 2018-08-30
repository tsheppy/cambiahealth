FROM openjdk:8-alpine

VOLUME /workdir
COPY . /usr/src/cambihealth/

WORKDIR /workdir
CMD ["java", "/usr/src/cambiahealth/cambiahealth.jar"]