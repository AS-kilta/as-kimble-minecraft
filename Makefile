.PHONY: all build clean

launch-server:
	cd ./server && screen -S minecraft ./start.sh

reload: deploy
	screen -S minecraft -p 0 -X stuff "`printf "reload\r"`"

deploy: build
	mv ./target/MinecraftASKimble-*-SNAPSHOT.jar ./server/plugins

build:
	mvn package

clean:
	rm -rf target