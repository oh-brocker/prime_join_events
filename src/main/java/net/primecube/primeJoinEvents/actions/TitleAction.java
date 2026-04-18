package net.primecube.primeJoinEvents.actions;

import net.primecube.primeJoinEvents.PrimeJoinEvents;
import net.primecube.primeJoinEvents.api.Action;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.time.Duration;

public record TitleAction(String main, String sub) implements Action {
    @Override
    public void execute(@NotNull Player player) {
        var plugin = PrimeJoinEvents.getInstance();
        player.showTitle(Title.title(
                plugin.parse(main, player),
                plugin.parse(sub, player),
                Title.Times.times(Duration.ofMillis(500), Duration.ofSeconds(3), Duration.ofMillis(500))
        ));
    }
}