#!/bin/bash

echo "deprecated, to be deleted!"

#del_stopped(){
#  local name=$1
#  local state=$(docker inspect --format "{{.State.Running}}" $name 2>/dev/null)
#
#  echo "Stopping docker container '$1'"
#
#  if [[ "$state" == "true" ]]; then
#    echo "running docker stop $name"
#    docker stop $name
#    echo "running docker rm $name"
#    docker rm $name
#  fi
#}
#
### Skysail Server Website
#del_stopped skysail-server-website-test
#
#sleep 5
#
#echo "Starting docker container!..."
#
#docker run \
#    -d
#    -t
#    --name skysail-server-website-test \
#    --rm
#    -p 9202:9202 \
#    -v /home/carsten/skysail/skysailconfigs/website/test:/home/skysail/products/demo/config \
#    -e CONFPATH=config \
#    evandor/skysail-server-website:latest
