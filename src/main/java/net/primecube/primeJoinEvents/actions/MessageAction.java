package net.primecube.primeJoinEvents.actions;

import net.primecube.primeJoinEvents.PrimeJoinEvents;
import net.primecube.primeJoinEvents.api.Action;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public record MessageAction(String text, boolean isBroadcast) implements Action {
    @Override
    public void execute(@NotNull Player player) {
        var component = PrimeJoinEvents.getInstance().parse(text, player);
        if (isBroadcast) {
            Bukkit.broadcast(component);
        } else {
            player.sendMessage(component);
        }
    }
}