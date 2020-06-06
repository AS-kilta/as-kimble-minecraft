#!/bin/sh

# select proper java version on macOS
if [[ "$OSTYPE" == "darwin"* ]]; then
    export JAVA_HOME=`/usr/libexec/java_home -v 1.8`
fi

java -Xms1G -Xmx1G -XX:+UseConcMarkSweepGC -DIReallyKnowWhatIAmDoingISwear -jar spigot-1.15.2.jar nogui
