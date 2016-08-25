package uk.co.proxying.cubecraft.listeners;

import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.material.Wool;
import org.bukkit.scheduler.BukkitRunnable;
import uk.co.proxying.cubecraft.CubeCraftTest;

/**
 * Created by Kieran Quigley (Proxying) on 24-Aug-16.
 */
public class PlayerListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerWalk(PlayerMoveEvent event) {
        final Location toLocation = event.getTo();
        if (event.getFrom().getBlock().equals(toLocation.getBlock())) return;
        if (toLocation.getBlock().getRelative(BlockFace.DOWN).getType() == Material.WOOL) {
            final Wool wool = new Wool(toLocation.getBlock().getRelative(BlockFace.DOWN).getType(), toLocation.getBlock().getRelative(BlockFace.DOWN).getData());
            final World world = event.getPlayer().getWorld();
            new BukkitRunnable() {
                int taskCounter;
                @Override
                public void run() {
                    if (taskCounter >= 5) {
                        this.cancel();
                        return;
                    }
                    world.spigot().playEffect(event.getPlayer().getLocation(), Effect.COLOURED_DUST, 0, 0, (float) wool.getColor().getColor().getRed() / 255, (float) wool.getColor().getColor().getGreen() / 255, (float) wool.getColor().getColor().getBlue() / 255, 1, 0, 30);
                    taskCounter++;
                }
            }.runTaskTimer(CubeCraftTest.getInstance(), 0L, 20L);
        }
    }
}
