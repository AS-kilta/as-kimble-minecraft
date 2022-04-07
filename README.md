# Minecraft AS Kimble

Minecraft server side client for the Aalto University Guild of Automation and Systems Technology (AS) [Kimble App](https://github.com/Spoam/as-kimble-mobiili).


# Setup

### IDE Setup
1. Install [IntelliJ IDEA](https://www.jetbrains.com/idea/) IDE.
2. Install [Java JDK](https://www.oracle.com/java/technologies/downloads/#java17). On Ubuntu you can install
the right JDK version with the command
    ```
    sudo apt install openjdk-17-jdk
    ```


### Project setup
1. Clone the project repository to your computer.
   ```
   git clone git@github.com:AS-kilta/as-kimble-minecraft.git
   ```
2. Open IntelliJ IDEA
3. Select `Open or Import` navigate to the location where you cloned the repository and select the repository root directory,
and click ok.
4. Once the project has loaded go to `File`>`Project Structure...` from the top menu and make sure that the Project SDK
uses the right java version.


### Development Server
The following commands should work out of the box on any UNIX based OS. For Windows, you should set up Windows Subsystem for Linux (WSL)
using the following [guide](https://docs.microsoft.com/en-us/windows/wsl/install-win10) and run the development server there.
1. Change working directory to `./server`
    
    **Linux / macOS**
    ```
    cd server
    ```
    **Windows with WSL**
    
    On WSL you can access the Windows filesystem under `/mnt/c`. As an example if the repository is located at
    `C:\Users\User\Code\as-kimble-minecraft` on the Windows file system, you access the directory on the WSL side with the command
    ```
    cd /mnt/c/Users/User/Code/as-kimble-minecraft/server
    ```
   
2. Download Spigot
    ```
    wget https://papermc.io/api/v2/projects/paper/versions/1.18.2/builds/282/downloads/paper-1.18.2-282.jar
    ```
   
3. Launch server. On first startup this will generate the world and various other config files which might take a while.
    ```
    ./start.sh
    ```
    On Windows completing the **IDE Setup** section only installs Java JDK on Windows, but you will also need to install it on WSL to run the server.
    Install the required Java runtime with the following command in the WSL terminal.
    ```
    sudo apt install openjdk-17-jdk
    ```
   
4. Launching the server should have generated a `plugins` directory. Place the compiled plugin jar file from `../target` to the `plugins`
directory e.g. with `cp ../target/*.jar plugins`, and then reload bukkit plugins with the `/reload` server command.    

You can use the script `cleanup.sh` to remove the world data and config files to reset the `server` directory to its original state.


# Compiling

In IntelliJ IDEA expand the panel `maven` on the right hand side of the window. Double click the menu item `package`
under `MinecraftASKimble`>`Lifecycle` to compile the plugin. The compiled plugin is placed in the `target` directory.

Alternatively you can invoke the maven package command from the terminal provided that you have it installed.
   ```
   mvn package
   ```

# Testing

Once you have compiled the plugin and placed it in `server/plugins` you can test it out by starting the development server and
connecting to it with the IP address `localhost` from Minecraft multiplayer menu.