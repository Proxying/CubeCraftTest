package uk.co.proxying.cubecraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Kieran Quigley (Proxying) on 24-Aug-16.
 */
public class CommandFizzBuzz implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 2) {
            int lowBound;
            try {
                lowBound = Integer.parseInt(args[0]);
            } catch (Exception ex) {
                commandSender.sendMessage("Your low input could not be read as an integer, setting it as 1.");
                lowBound = 1;
            }
            int highBound;
            try {
                highBound = Integer.parseInt(args[1]);
            } catch (Exception ex) {
                commandSender.sendMessage("Your high input could not be read as an integer, setting it as 100.");
                highBound = 100;
            }
            String output;
            String result = "";
            while (lowBound < highBound) {
                output = "";
                if (lowBound % 3 == 0) {
                    output = "Fizz";
                }
                if (lowBound % 5 == 0) {
                    output += "Buzz";
                }
                if (output.equals("")) {
                    output = Integer.toString(lowBound);
                }
                result += output + ", ";
                lowBound++;
            }

            output = "";
            if (lowBound % 3 == 0) {
                output = "Fizz";
            }
            if (lowBound % 5 == 0) {
                output += "Buzz";
            }
            if (output.equals("")) {
                output = Integer.toString(lowBound);
            }

            result += output + ".";
            //This can be done via getting the string and replacing the last "," with ".". But I personally prefer this way.

            commandSender.sendMessage(result);
            return true;
        }
        return false;
    }
}
