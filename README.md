# PacketLimiter

**PacketLimiter** is a plugin for [PowerNukkitX](https://github.com/PowerNukkitX/PowerNukkitX) that limits the number of packets a player can send per second. Players exceeding the packet limit will receive warnings, and after reaching the maximum number of warnings, they will be kicked from the server.

## Features

- Limits packet sending per player
- Warning system before kicking
- Auto-kick after exceeding warning limit

## Configuration

The plugin uses the following settings (add manually or hardcode):

```yaml
maxWarnings: 3
packetLimit: 100
kickMessage: "You have been kicked for sending too many packets!"
```