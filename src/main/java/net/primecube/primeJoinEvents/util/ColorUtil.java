package net.primecube.primeJoinEvents.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class ColorUtil {
    private static final MiniMessage MM = MiniMessage.miniMessage();

    public static @NotNull Component parse(@NotNull String text, @NotNull Player player) {
        return MM.deserialize(text
                .replace("%player%", player.getName())
                .replace("%online%", String.valueOf(player.getServer().getOnlinePlayers().size()))
        );
    }
}