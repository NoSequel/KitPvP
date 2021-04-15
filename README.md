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

# Pull Request Rules
* Preferably no lambda expressions at all, use normal loops whenever possible (if it makes sense, you can obviously use the stream api).
* Use dependency injection, and no static variables (unless it's a value inside of the KitConstants.java class)
* Use finals whenever possible - even in methods, this is to maintain consistency with everything else, and makes it more readable.
* Don't implement external dependencies for a single method - if it's a single method or utility, rewrite it, otherwise you're implementing classes you don't actually use.
