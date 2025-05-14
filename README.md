# PacketLimiter
<img src="icon.png" width="250" />

**PacketLimiter** is a plugin for [PowerNukkitX](https://github.com/PowerNukkitX/PowerNukkitX) that limits the number of packets a player can send per second. Players exceeding the packet limit will receive warnings, and after reaching the maximum number of warnings, they will be kicked from the server.

## Features

- Limits packet sending per player
- Warning system before kicking
- Auto-kick after exceeding warning limit

## Configuration

The plugin uses the following settings (add manually or hardcode):

```yaml
# Maximum number of warnings before a player is kicked
maxWarnings: 3

# Limit on the number of packets that can be received per second per player
packetLimit: 100

# Message displayed when a player is kicked due to packet spam
kickMessage: "You have been kicked for sending too many packets!"

```