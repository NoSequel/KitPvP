# KitPvP Core
This is an opensource KitPvP core, made for fun out of boredom, basically unfinished.

## Features
* A basic kit system
* 3 pre-made kits, concluding:
    * Default Kit
    * Intoxicator Kit
    * Launcher Kit
* A basic killstreak system, including 1 premade killstreak:
    * Kit Resetter (5 kills)
* Regions, saving in the ``config.yml``
* Profiles, saving in the ``config.yml``

# Why did I choose to use flatfile instead of a database?
Because the data on the server is meant to be for 1 server instance only - it does not have to get synchronized across networks.