package uk.co.proxying.cubecraft;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import uk.co.proxying.cubecraft.commands.*;
import uk.co.proxying.cubecraft.listeners.PlayerListener;
import uk.co.proxying.cubecraft.objects.EditableSign;

import java.util.logging.Logger;

/**
 * Created by Kieran Quigley (Proxying) on 24-Aug-16.
 */
public final class CubeCraftTest extends JavaPlugin {

    private static final Logger log = Logger.getLogger(CubeCraftTest.class.getSimpleName());
    private static CubeCraftTest instance = null;

    @Override
    public void onEnable() {
        log.info("[CubeCraftTest] Enabling...");
        instance = this;
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

        getCommand("sneak").setExecutor(new CommandToggleSneak());
        getCommand("stand").setExecutor(new CommandToggleStand());
        getCommand("fizzbuzz").setExecutor(new CommandFizzBuzz());
        getCommand("primitive").setExecutor(new CommandPrimitive());
        getCommand("sign").setExecutor(new CommandSign());
        getCommand("menu").setExecutor(new CommandMenu());

        UtilityTasks.getInstance().runTasks();
        EditableSign.getInstance().runTasks();
    }

    @Override
    public void onDisable() {
        log.info("[CubeCraftTest] Disabling...");
    }

    public static CubeCraftTest getInstance() {
        return instance;
    }
}
