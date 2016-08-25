package uk.co.proxying.cubecraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Kieran Quigley (Proxying) on 24-Aug-16.
 */
public class CommandPrimitive implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 1) {
            char[] chars = args[0].toCharArray();

            int result = 0, digit, multiplier = 1;
            boolean isNegative = false;


            for (int i = (chars.length - 1); i > -1; i--) {
                if (chars[i] == '-') {
                    if (i == 0) {
                        isNegative = true;
                    }
                    continue;
                }
                digit = chars[i] - '0';
                digit *= multiplier;
                result = result + digit;
                multiplier *= 10;
            }

            commandSender.sendMessage("Input: " + args[0] + ", Return: " + (!isNegative ? Integer.toString(result) : Integer.toString(result * -1)));

            return true;
        }
        return false;
    }
}
