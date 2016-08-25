package uk.co.proxying.cubecraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import uk.co.proxying.cubecraft.objects.Menu;

/**
 * Created by Kieran Quigley (Proxying) on 25-Aug-16.
 */
public class CommandMenu implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                commandSender.sendMessage("Target is not online.");
                return true;
            }
            Menu menu = new Menu(target, "Wow a menu.", 9) {
                @Override
                public void onClick(InventoryClickEvent event, int slot) {
                    event.getWhoClicked().sendMessage("You clicked: " + event.getRawSlot());
                }
            }.setItemInSlot(new ItemStack(Material.BANNER), 1).setItemInSlot(new ItemStack(Material.APPLE), 5);

            menu.openFor(target);
            return true;
        }
        return false;
    }
}
