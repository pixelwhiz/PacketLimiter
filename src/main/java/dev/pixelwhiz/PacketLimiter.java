package dev.pixelwhiz;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.plugin.PluginBase;
import dev.pixelwhiz.session.SessionManager;
import dev.pixelwhiz.task.PacketLimiterTask;

public class PacketLimiter extends PluginBase implements Listener {

    @Override
    public void onEnable() {
        this.saveResource("config.yml");
        int maxWarn = this.getConfig().getInt("maxWarnings", 3);
        int packetLimit = this.getConfig().getInt("packetLimit", 100);
        String kickMsg = this.getConfig().getString("kickMessage", "Kicked for packet spam!");
        SessionManager.create(maxWarn, packetLimit, kickMsg);

        Server.getInstance().getScheduler()
                .scheduleRepeatingTask(new PacketLimiterTask(), 1); // tick interval
        Server.getInstance().getPluginManager().registerEvents(this, this);

        super.onEnable();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        SessionManager.getInstance().add(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onReceive(DataPacketReceiveEvent event) {
        Player player = event.getPlayer();
        if (player == null || !player.isOnline()) return;
        SessionManager.getInstance().get(player).addPacket();
    }


    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onQuit(PlayerQuitEvent event) {
        SessionManager.getInstance().remove(event.getPlayer());
    }

}
