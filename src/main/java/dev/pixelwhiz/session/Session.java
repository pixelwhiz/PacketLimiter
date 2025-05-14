package dev.pixelwhiz.session;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerKickEvent;

public class Session {

    private final SessionManager sessionManager;
    private final Player player;
    private int warnings = 0;
    private int packetCount = 0;
    private double lastCheckTime = 0;

    public Session(SessionManager sessionManager, Player player) {
        this.sessionManager = sessionManager;
        this.player = player;
    }


    public void addPacket() {
        packetCount++;
    }

    public void check(double currentTime) {
        double elapsedTime = currentTime - lastCheckTime;
        lastCheckTime = currentTime;

        int limit = (elapsedTime >= 1.2) ?
                (int) (sessionManager.getPacketLimit() * elapsedTime) :
                sessionManager.getPacketLimit();

        if (packetCount >= limit) {
            addWarning();
        }

        packetCount = 0;
    }

    public void addWarning() {
        warnings++;

        if (warnings >= sessionManager.getMaxWarn()) {
            player.kick(
                    PlayerKickEvent.Reason.KICKED_BY_ADMIN,
                    sessionManager.getKickMessage(),
                    true
            );
        }
    }

}
