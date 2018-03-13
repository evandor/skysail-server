# http://paulbakker.io/docker/docker-osgi/
#
# In project root dir:
#
# ./gradlew clean build skysail.core:export.core.int skysail.core:runnable
#
# cd skysail.core/etc/release
#
# docker build -t evandor/skysail-server .
#
# docker rmi $(docker images -f "dangling=true" -q)
# docker stop $(docker ps -a -q)
# docker container prune
# docker run -ti --rm -p 9102:9102 evandor/skysail-server
# docker login
# docker push evandor/skysail-server
#
# run on sofia:
# sudo docker run --name skysail-server-website -ti --rm -p 9202:9202 evandor/skysail-server-website:latest
#
# access running container on sofia
# sudo docker exec -i -t skysail-server-website /bin/bash
FROM java
ARG CACHEBUST=1
RUN echo "building docker image from directory '$pwd'"
RUN echo "executing 'useradd -d /home/skysail -m -s /bin/bash skysail'"
RUN useradd -d /home/skysail -m -s /bin/bash skysail
USER skysail
ENV HOME /home/skysail
WORKDIR /home/skysail

RUN mkdir -m 775 -p /home/skysail/products/demo/plugins
RUN mkdir -m 775 -p /home/skysail/products/demo/config

#COPY --chown=skysail config/ /home/skysail/products/demo/config/
#COPY --chown=skysail website.docker.jar /home/skysail/products/demo/server.website.jar

COPY config/ /home/skysail/products/demo/config/
COPY website.docker.jar /home/skysail/products/demo/server.website.jar

WORKDIR /home/skysail/products/demo

#ENTRYPOINT exec java -Dskysail.config.path=$CONFPATH -jar /home/skysail/products/demo/server.website.jar
CMD ["java","-jar","/home/skysail/products/demo/server.website.jar"]

EXPOSE 9102