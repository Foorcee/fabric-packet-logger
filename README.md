# Fabric Packet Logger

There weren't really any good fabric packet loggers out on Modrinth, so I decided to create my own one. I use this for development at [thejocraft.net](thejocraft.net)

## Features

- Web-UI (Default Port 8080)
- Websocket Connection (Default Port 1337)
- InGame config
- TODO: InGame packet history viewer

## Commands

- ``/packetlogger`` is the base command for all subcommands. You should be able to auto-complete them
- ``/packetlogger toggleLogging`` - toggles logging
- ``/packetlogger open`` - opens the web-ui

## Websocket

By default, the web-ui connects to the websocket and receives the packet information via json data. You can also connect to this websocket as well and receive the same data as the web-ui. I'd recommend using [insomnia](https://insomnia.rest/) or [weasel](https://addons.mozilla.org/de/firefox/addon/websocket-weasel/) on firefox.

## Web-UI

I have a different React project locally which contains the code for the web-ui. I will probably port it to a single html file at some point as otherwise a http server is running parallel with minecraft. In the end, it's mainly a tool I developed for my own purpose, and it was never meant to be used by others.

Previews:

[![Preview](https://cdn.discordapp.com/attachments/598256161212596235/1092402886710927370/image.png)](https://google.com/)