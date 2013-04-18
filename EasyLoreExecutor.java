package com.github.techietotoro.EasyLore;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EasyLoreExecutor implements CommandExecutor {
	 
	// may remove this sometime
	private EasyLore plugin; // pointer to your main class, unrequired if you don't need methods from the main class
 
	public EasyLoreExecutor(EasyLore plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
    	if(cmd.getName().equalsIgnoreCase("lore")){ // If the player typed /lore then do the following...
    		if (!(sender instanceof Player)) {
    			sender.sendMessage("This command can only be run by a player.");
    			return true;
    		} else if (args.length > 0) { //sanitize argument count here!!!
    			// get next argument and remove from array
    			String cmd1 = args[0];
    			String[] tempArgs = new String[args.length - 1];
    			for (int i = 1; i < args.length; i++) {
    				tempArgs[i-1] = args[i];
    			}
    			args = tempArgs;
    			
    			// handle secondary command. eg: /lore <command>
    			if (cmd1.equalsIgnoreCase("set")) {
    				Player player = (Player) sender;
        			// get the item in hand
        			ItemStack itemInHand = player.getItemInHand();
        			ItemMeta itemMeta = itemInHand.getItemMeta();
        			// parse the String[]
        			String newLore = new String();
        			for (String s : args) {
        				newLore = newLore + " " + s;
        			}
        			// edit the lore of the item in hand with the string
        			// first create empty list, then add lore string
        			ArrayList<String> newList = new ArrayList<String>(1);
        			newList.add(newLore);
        			// set lore
        			itemMeta.setLore(newList);
        			itemInHand.setItemMeta(itemMeta);
        			return true;
    			}
    			else if (cmd1.equalsIgnoreCase("replace")) {
    				// requires line number to set
    			
    				return true;
    			}
    			else if (cmd1.equalsIgnoreCase("add")) {
    				// take current ItemMeta and add to the lore
    				
    				return true;
    			}
    			else if (cmd1.equalsIgnoreCase("remove")) {
    				
    				return true;
    			}
    			
    		}
    		// display a help menu for /lore here
    		
    		return true;
    	} //If this has happened the function will return true. 
            // If this hasn't happened the a value of false will be returned.
    	return false; 
    }
}