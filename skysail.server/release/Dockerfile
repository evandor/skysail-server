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
# sudo docker run --name skysail-server -ti --rm -p 9102:9102 evandor/skysail-server:latest
#
# access running container on sofia
# sudo docker exec -i -t skysail-server /bin/bash
FROM java
RUN echo "building docker image from directory '$pwd'"
RUN echo "executing 'useradd -d /home/skysail -m -s /bin/bash skysail'"
RUN useradd -d /home/skysail -m -s /bin/bash skysail
USER skysail
ENV HOME /home/skysail
WORKDIR /home/skysail

RUN mkdir -p /home/skysail/products/demo/plugins

COPY config/ /home/skysail/products/demo/config/
COPY server.int.jar /home/skysail/products/demo/server.int.jar


WORKDIR /home/skysail/products/demo

CMD ["java","-jar","/home/skysail/products/demo/server.int.jar"]

EXPOSE 9102