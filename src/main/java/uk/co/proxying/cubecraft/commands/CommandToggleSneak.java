package uk.co.proxying.cubecraft.commands;

import net.minecraft.server.v1_9_R2.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by Kieran Quigley (Proxying) on 24-Aug-16.
 */
public class CommandToggleSneak implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                commandSender.sendMessage("Target is not online.");
                return true;
            }
            Packet packet;
            DataWatcher dataWatcher;

            EntityPlayer targetNMS = ((CraftPlayer) target).getHandle();

            for (Player player : Bukkit.getOnlinePlayers()) {
                dataWatcher = new DataWatcher(((CraftPlayer) player).getHandle());
                dataWatcher.register(new DataWatcherObject<>(0, DataWatcherRegistry.a), (byte) 0x02);
                packet = new PacketPlayOutEntityMetadata(player.getEntityId(), dataWatcher, true);
                targetNMS.playerConnection.sendPacket(packet);
            }

            commandSender.sendMessage(target.getDisplayName() + " should see everyone sneaking now.");
            return true;
        }
        return false;
    }
}
