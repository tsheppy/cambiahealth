FROM openjdk:8-alpine

ARG VERSION
ARG BUILD_DATE

LABEL org.label-schema.vendor="Tucker Sheppy" \
      org.label-schema.url="https://github.com/tsheppy/cambiahealth/" \
      org.label-schema.name="Interview Project" \
      org.label-schema.description="Sample project for SDET interview at Cambia Health" \
      org.label-schema.version=$VERSION \
      org.label-schema.build-date=$BUILD_DATE \
      org.label-schema.schema-version="1.0"

COPY . /usr/src/cambihealth/
WORKDIR /usr/src/cambihealth/

CMD ["java", "-jar", "cambiahealth.jar"]
