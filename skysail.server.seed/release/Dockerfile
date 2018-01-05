FROM java
RUN echo "building docker image from directory '$pwd'"
RUN echo "executing 'useradd -d /home/skysail -m -s /bin/bash skysail'"
RUN useradd -d /home/skysail -m -s /bin/bash skysail
USER skysail
ENV HOME /home/skysail
WORKDIR /home/skysail

RUN mkdir -p /home/skysail/products/demo/plugins

COPY config/ /home/skysail/products/demo/config/
COPY server.seed.docker.jar /home/skysail/products/demo/server.seed.jar


WORKDIR /home/skysail/products/demo

CMD ["java","-jar","/home/skysail/products/demo/server.seed.jar"]

EXPOSE 9102