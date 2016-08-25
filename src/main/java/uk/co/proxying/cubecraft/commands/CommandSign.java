package uk.co.proxying.cubecraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.co.proxying.cubecraft.objects.EditableSign;

/**
 * Created by Kieran Quigley (Proxying) on 25-Aug-16.
 */
public class CommandSign implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                commandSender.sendMessage("Target is not online.");
                return true;
            }

            EditableSign.getInstance().createSignGUI(target, entry -> entry.forEach(target::sendMessage));
            return true;
        }
        return false;
    }
}
