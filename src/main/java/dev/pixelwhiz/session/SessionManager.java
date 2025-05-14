package dev.pixelwhiz.session;

import cn.nukkit.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {

    private static SessionManager instance;

    private final Map<Player, Session> sessions = new ConcurrentHashMap<>();
    private final int maxWarn;
    private final int packetLimit;
    private final String kickMessage;

    public static void create(int maxWarn, int packetLimit, String kickMessage) {
        instance = new SessionManager(maxWarn, packetLimit, kickMessage);
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("SessionManager is not open");
        }
        return instance;
    }

    private SessionManager(int maxWarn, int packetLimit, String kickMessage) {
        this.maxWarn = maxWarn;
        this.packetLimit = packetLimit;
        this.kickMessage = kickMessage;
    }

    public void add(Player player) {
        sessions.put(player, new Session(this, player));
    }

    public void remove(Player player) {
        sessions.remove(player);
    }

    public Session get(Player player) {
        if (player == null) return null;
        return sessions.computeIfAbsent(player, p -> new Session(this, p));
    }


    public void check(double time) {
        sessions.values().forEach(session -> session.check(time));
    }

    public int getMaxWarn() {
        return maxWarn;
    }

    public int getPacketLimit() {
        return packetLimit;
    }

    public String getKickMessage() {
        return kickMessage;
    }


}