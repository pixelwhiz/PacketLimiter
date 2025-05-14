package dev.pixelwhiz.task;

import cn.nukkit.scheduler.Task;
import dev.pixelwhiz.session.SessionManager;

public class PacketLimiterTask extends Task {

    private double lastCheckTime = System.currentTimeMillis() / 1000.0;

    @Override
    public void onRun(int i) {
        final double currentTime = System.currentTimeMillis() / 1000.0;
        final double elapsed = currentTime - lastCheckTime;

        if(elapsed >= 1.0) {
            SessionManager.getInstance().check(elapsed);
            lastCheckTime = currentTime;
        }
    }

}
