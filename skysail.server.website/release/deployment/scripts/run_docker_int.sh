#!/bin/bash

del_stopped(){
  local name=$1
  local state=$(docker inspect --format "{{.State.Running}}" $name 2>/dev/null)

  if [[ "$state" == "true" ]]; then
    docker stop $name
    docker rm $name
  fi
}

## Skysail Server Website
del_stopped skysail-server-website-int
docker run \
    --name skysail-server-website-int \
    -t --rm -p 9201:9201 \
    -v /home/carsten/skysail/skysailconfigs/website/int:/home/skysail/products/demo/config \
    -e CONFPATH=config \
    evandor/skysail-server-website:latest
