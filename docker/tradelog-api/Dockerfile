FROM debian:buster-slim

# prepare linux box
RUN apt-get update
RUN apt-get install -y wget
RUN apt-get install -y netcat

# install openjdk-11 x64
RUN wget https://download.java.net/openjdk/jdk11/ri/openjdk-11+28_linux-x64_bin.tar.gz -O /tmp/openjdk-11+28_linux-x64_bin.tar.gz
RUN mkdir /usr/lib/jvm
RUN tar xfvz /tmp/openjdk-11+28_linux-x64_bin.tar.gz --directory /usr/lib/jvm
RUN rm -f /tmp/openjdk-11+28_linux-x64_bin.tar.gz

# prepare java env
ENV JAVA_HOME="/usr/lib/jvm/jdk-11/bin"

EXPOSE 8083

# copy java app
VOLUME /tmp
COPY ./services/tradelog/tradelog-api/build/libs/tradelog-api-0.0.1-SNAPSHOT.jar /app/app.jar
COPY ./docker/tradelog-api/entrypoint.sh /app/entrypoint.sh
RUN chmod 755 /app/entrypoint.sh
ENTRYPOINT ["/app/entrypoint.sh"]
