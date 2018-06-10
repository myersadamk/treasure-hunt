#!/bin/bash
#
# Runner script for the treasure-hunt app. Pass --help to the script to get application usage info.
#
# Execute this script in the reactor base directory via './treasure-hunt.sh <args>' or 'bash treasure-hunt.sh <args>'
# If running from a Windows machine and Git Bash is not available, it's sufficient to execute 'mvn install' and then
# use the following command:
#
# 'java --add-opens java.base/java.lang=ALL-UNNAMED -jar treasure-hunt-app/target/treasure-hunt-app-1.0-SNAPSHOT.jar <args>'
#

TAR_PATH='treasure-hunt-app/target/treasure-hunt-app-1.0-SNAPSHOT.jar'
if [[ ! -e $TAR_PATH ]]
then
    mvn install
fi

# --add-opens prevents JRE warnings caused by some combinations of Spring and JDK9+
# The issue causing the warnings is fixed in the current milestone version of Spring.

java --add-opens java.base/java.lang=ALL-UNNAMED -jar $TAR_PATH $@
