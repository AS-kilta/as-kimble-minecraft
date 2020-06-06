#! /bin/sh

### Clean up server directory

# config files
rm banned-ips.json banned-players.json bukkit.yml commands.yml help.yml permissions.yml spigot.yml usercache.json whitelist.json ops.json

# world data
rm -r world world_nether world_the_end

# other directories
rm -r logs plugins
