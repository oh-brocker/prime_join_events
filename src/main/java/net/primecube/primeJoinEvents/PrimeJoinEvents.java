package net.primecube.primeJoinEvents;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.primecube.primeJoinEvents.commands.PjeCommand;
import net.primecube.primeJoinEvents.listeners.JoinLeaveListener;
import net.primecube.primeJoinEvents.util.ColorUtil;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class PrimeJoinEvents extends JavaPlugin {
    private static PrimeJoinEvents instance;
    private final MiniMessage miniMessage = MiniMessage.miniMessage();
    private final Component prefix = miniMessage.deserialize("<gradient:#4facfe:#00f2fe><bold>PrimeCubeNetwork</bold></gradient> <dark_gray>» </dark_gray>");

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        var cmd = new PjeCommand(this);
        var pluginCmd = getCommand("pje");
        if (pluginCmd != null) {
            pluginCmd.setExecutor(cmd);
            pluginCmd.setTabCompleter(cmd);
        }

        getServer().getPluginManager().registerEvents(new JoinLeaveListener(this), this);

        getComponentLogger().info(miniMessage.deserialize("<gradient:#4facfe:#00f2fe>============================================</gradient>"));
        getComponentLogger().info(miniMessage.deserialize("<white>   PrimeJoinEvents <green>УВІМКНЕНО ТРЯСЦЯ!</green></white>"));
        getComponentLogger().info(miniMessage.deserialize("<gradient:#4facfe:#00f2fe>============================================</gradient>"));
    }

    @Override
    public void onDisable() {
        getComponentLogger().info(miniMessage.deserialize("<red>PrimeJoinEvents вимкнено. Очищення пам'яті...</red>"));
        instance = null;
    }

    public static PrimeJoinEvents getInstance() { return instance; }
    public MiniMessage getMiniMessage() { return miniMessage; }
    public Component getPrefix() { return prefix; }
    public Component parse(String text, Player player) { return ColorUtil.parse(text, player); }
}