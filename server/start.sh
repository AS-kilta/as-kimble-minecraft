#!/bin/bash

# select proper java version on macOS
if [[ "$OSTYPE" == "darwin"* ]]; then
	export JAVA_HOME=`/usr/libexec/java_home -v 1.8`
fi

java -Xms1G -Xmx1G -DIReallyKnowWhatIAmDoingISwear -jar paper-1.18.2-282.jar nogui
