package net.primecube.primeJoinEvents.commands;

import net.kyori.adventure.text.Component;
import net.primecube.primeJoinEvents.PrimeJoinEvents;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class PjeCommand implements CommandExecutor, TabCompleter {
    private final PrimeJoinEvents plugin;

    public PjeCommand(PrimeJoinEvents plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("info")) {
            sender.sendMessage(Component.text(" "));
            sender.sendMessage(plugin.getMiniMessage().deserialize("<gradient:#4facfe:#00f2fe><bold>PrimeJoinEvents ver. 1.0</bold></gradient>"));
            sender.sendMessage(plugin.getMiniMessage().deserialize("<gray>Автор: <gradient:#f6d365:#fda085>BrockerDev</gradient>"));
            sender.sendMessage(plugin.getMiniMessage().deserialize("<gray>Статус: <green>Стабільна версія</green>"));
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("pje.admin")) {
                sender.sendMessage(
                        plugin.getPrefix().append(
                                plugin.getMiniMessage().deserialize("<red>У тебе немає прав на цю команду.</red>")
                        )
                );
                return true;
            }

            plugin.reloadConfig();
            sender.sendMessage(
                    plugin.getPrefix().append(
                            plugin.getMiniMessage().deserialize("<green>Конфіг перезавантажено!</green>")
                    )
            );
            return true;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender s, @NotNull Command c, @NotNull String a, String[] args) {
        return args.length == 1 ? List.of("reload", "info") : List.of();
    }
}
