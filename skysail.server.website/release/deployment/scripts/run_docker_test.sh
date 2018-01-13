#!/bin/bash

del_stopped(){
  local name=$1
  local state=$(docker inspect --format "{{.State.Running}}" $name 2>/dev/null)

  echo "Stopping docker container '$1'"

  if [[ "$state" == "true" ]]; then
    echo "running docker -D stop $name"
    docker -D stop $name
    echo "running docker -D rm $name"
    //docker -D rm $name
  fi
}

## Skysail Server Website
del_stopped skysail-server-website-test

echo "Starting docker container..."

docker run -D\
    --name skysail-server-website-test \
    -t --rm -p 9202:9202 \
    -v /home/carsten/skysail/skysailconfigs/website/test:/home/skysail/products/demo/config \
    -e CONFPATH=config \
    evandor/skysail-server-website-local:latest
