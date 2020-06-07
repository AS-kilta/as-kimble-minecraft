# Minecraft AS Kimble

Minecraft client for the Guild of Automation and Systems Technology (AS) [Kimble App](https://github.com/Spoam/as-kimble-mobiili).


# Setup

### IDE Setup
1. Install [IntelliJ IDEA](https://www.jetbrains.com/idea/) IDE.
2. Install [Java JDK](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html). On Ubuntu you can install
the right JDK version with the command
    ```
    sudo apt install openjdk-8-jdk
    ```


### Project setup
1. Clone the project repository to your computer.
   ```
   git clone git@gitlab.com:asdvek/minecraft_as_kimble.git
   ```
2. Open IntelliJ IDEA
3. Select `Open or Import` navigate to the location where you cloned the repository and select the repository root directory,
and click ok.
4. Once the project has loaded go to `File`>`Project Structure...` from the top menu and make sure that the Project SDK
uses Java JDK version 8 or 1.8.


### Development Server
1. Change working directory to `./server`
    ```
    cd server
    ```
2. Download Spigot
    
    **Linux / macOS**
    ```
    wget https://cdn.getbukkit.org/spigot/spigot-1.15.2.jar
    ```
    **Windows**
    ```
    curl.exe https://cdn.getbukkit.org/spigot/spigot-1.15.2.jar --output spigot-1.15.2.jar
    ```
   
3. Launch server. On first startup this will generate the world and various other config files which might take a while.

    **Linux / macOS**
    ```
    ./start.sh
    ```
   
    **Windows**
    ```
    start.bat
    ```
4. Launching the server should have generated a `plugins` directory. Place the compiled plugin jar file from `../target` to the `plugins`
directory and then reload bukkit plugins with the `/reload` server command.

    You can use the scripts `cleanup.sh` and `cleanup.bat` on Linux/macOS and Windows respectively to remove the world data and
    config files to reset the `server` directory to its original state.

# Compiling

In IntelliJ IDEA expand the panel `maven` on the right hand side of the window. Double click the menu item `package`
under `MinecraftASKimble`>`Lifecycle` to compile the plugin. The compiled plugin is placed in the `target` directory.