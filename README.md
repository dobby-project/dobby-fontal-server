# Dobby server

What is Dobby ? Dobby is an intermediate server who simplify communications between clients using websockets and our apps.

The main purpose is to hide some troubles to the apps by providing an environment which is predictable. Indeed, we've already experienced some difficulties running properly a game with raw websockets. By this dedicated software, we want to follow the UNIX Philosophy "do one thing and do it well". This software will provide a steadiest environment to our game and will be designed to be a plateform that can accomodate several games.

The functionalities currently discussed are the following:
* authentication (through external service)
* managing groups
* auto-launching app instance when it's required
* hall of fame (through external service)
* instant messaging (demo app?)
* communication API
