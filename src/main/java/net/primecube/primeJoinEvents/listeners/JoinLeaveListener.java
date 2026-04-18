package net.primecube.primeJoinEvents.listeners;

import net.primecube.primeJoinEvents.PrimeJoinEvents;
import net.primecube.primeJoinEvents.actions.MessageAction;
import net.primecube.primeJoinEvents.actions.SoundAction;
import net.primecube.primeJoinEvents.actions.TitleAction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public final class JoinLeaveListener implements Listener {
    private final PrimeJoinEvents plugin;

    public JoinLeaveListener(PrimeJoinEvents plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(@NotNull PlayerJoinEvent event) {
        var player = event.getPlayer();
        event.joinMessage(null);

        String type = player.hasPlayedBefore() ? "regular" : "first-join";
        var section = plugin.getConfig().getConfigurationSection("events." + type);
        if (section == null) return;

        // Виконання масиву дій
        new MessageAction(section.getString("chat"), true).execute(player);
        new TitleAction(section.getString("title-main"), section.getString("title-sub")).execute(player);

        var s = section.getConfigurationSection("sound");
        if (s != null) {
            new SoundAction(s.getString("key"), (float)s.getDouble("pitch"), s.getInt("volume"), s.getString("source")).execute(player);
        }
    }
}