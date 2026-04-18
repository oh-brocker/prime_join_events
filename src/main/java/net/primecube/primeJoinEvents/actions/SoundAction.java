package net.primecube.primeJoinEvents.actions;

import net.primecube.primeJoinEvents.api.Action;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public record SoundAction(String key, float pitch, int volume, String source) implements Action {
    @Override
    public void execute(@NotNull Player player) {
        Sound.Source soundSource = switch (source.toUpperCase()) {
            case "PLAYER" -> Sound.Source.PLAYER;
            case "MUSIC" -> Sound.Source.MUSIC;
            case "VOICE" -> Sound.Source.VOICE;
            default -> Sound.Source.MASTER;
        };
        player.playSound(Sound.sound(Key.key(key), soundSource, volume, pitch));
    }
}