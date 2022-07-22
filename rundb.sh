#!/usr/bin/bash


docker run --name loginservicedb --rm -p 5432:5432 -e  POSTGRES_LOGIN_SERVICE_DB=db1 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password test-wa2
