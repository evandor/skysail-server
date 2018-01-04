#!/bin/bash

del_stopped(){
  local name=$1
  local state=$(docker inspect --format "{{.State.Running}}" $name 2>/dev/null)

  if [[ "$state" == "true" ]]; then
    docker stop $name
    docker rm $name
  fi
}

## Skysail Server
del_stopped skysail-server-demo
docker run --name skysail-server-demo -t --rm -p 9102:9102 evandor/skysail-server-demo:latest
