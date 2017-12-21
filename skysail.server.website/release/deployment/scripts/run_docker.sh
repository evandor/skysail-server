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
del_stopped skysail-server-website
docker run --name skysail-server-website -t --rm -p 9202:9202 -v /home/carsten/tmp:/home/skysail/products/demo/config2 -e "CONF_PATH=/tmp" evandor/skysail-server-website:latest
