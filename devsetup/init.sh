#!/usr/bin/env bash

USERNAME="admin"
PASSWORD="123qwe"

docker run -d --rm --name he-couchbase -p 8091-8094:8091-8094 -p 11210:11210 couchbase:6.0.1

sleep 20

docker exec -it he-couchbase /opt/couchbase/bin/couchbase-cli cluster-init -c 127.0.0.1 \
--cluster-username=${USERNAME} \
--cluster-password=${PASSWORD} \
--cluster-port=8091 \
--cluster-ramsize=256

docker exec -it he-couchbase /opt/couchbase/bin/couchbase-cli bucket-create -c 127.0.0.1:8091 \
--bucket=url \
--bucket-type=couchbase \
--bucket-ramsize=128 \
--bucket-replica=1 \
-u ${USERNAME} -p ${PASSWORD}

docker exec -it he-couchbase /opt/couchbase/bin/couchbase-cli user-manage -c 127.0.0.1:8091 \
-u ${USERNAME} \
-p ${PASSWORD} \
--set --rbac-username url --rbac-password url123  \
--rbac-name "sample" \
--roles admin \
--auth-domain local