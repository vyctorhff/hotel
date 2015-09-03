#!/usr/bin/env bash

TRUE="1"
FALSE="0"

PROJETO_HOME=.
FOLDER_TARGET="target"
JAR_FILE=""

setJarFile() {
    JAR_FILE=$(ls target/*jar-with-dependencies.jar)
}

existeTarget() {
    test -e "target" && echo ${TRUE} || echo ${FALSE}
}

existeJar() {
    test -e ${FOLDER_TARGET}/${JAR_FILE} && echo ${TRUE} || echo ${FALSE}
}

if [ $( existeTarget ) -eq ${FALSE} ]
then
    echo "The folder target not exist!"
    exit 1
fi

if [ $( existeJar ) -eq ${TRUE} ]
then
    setJarFile
    java -jar ${JAR_FILE} $1
else
    echo "Jar file not exist!"
fi
