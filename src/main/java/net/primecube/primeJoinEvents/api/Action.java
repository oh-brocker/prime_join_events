package net.primecube.primeJoinEvents.api;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface Action {
    void execute(@NotNull Player player);
}