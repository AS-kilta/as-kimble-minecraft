# Minecraft AS Kimble

Minecraft client for the Guild of Automation and Systems Technology (AS) [Kimble App](https://github.com/Spoam/as-kimble-mobiili).


# Setup

### IDE Setup
1. Install [IntelliJ IDEA](https://www.jetbrains.com/idea/) IDE.
2. Install [Java JDK](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html). Alternatively you can download the IDE from within the IDE.

### Project setup
1. Clone the project repository to your computer.
   ```
   git clone git@gitlab.com:asdvek/minecraft_as_kimble.git
   ```

### Development Server
1. Change working directory to `./server`
    ```
    cd server
    ```
2. Download Spigot
    ```
    wget https://cdn.getbukkit.org/spigot/spigot-1.15.2.jar
    ```
3. Launch server. On first startup this will generate the world and various other config files which might take a while.
    ```
    ./start.sh
    ```
4. Launching the server should have generated a `plugins` directory. Place the compiled plugin jar file from `../target` to the `plugins`
directory and then reload bukkit plugins with the `/reload` server command.

You can use the script `cleanup.sh` to remove the world data and config files to reset the `server` directory to its original state.