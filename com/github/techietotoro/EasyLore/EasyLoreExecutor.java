package com.github.techietotoro.EasyLore;

import java.util.ArrayList;

import org.bukkit.ChatColor;
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
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("help")) {
						// display EasyLore help here
						displayHelp();
						return true;
					} else {
						EasyChat.sendMessage(sender, "This command can only be run by a player.");
					}
				} else {
				EasyChat.sendMessage(sender, "This command can only be run by a player.");
				}
				return true;
			} else if (args.length > 0) { //sanitize argument count here!!!
				// get next argument and remove from array
				String cmd1 = args[0];
				String[] tempArgs = new String[args.length - 1];
				for (int i = 1; i < args.length; i++) {
					tempArgs[i-1] = args[i];
				}
				args = tempArgs;
				
				
				Player player = (Player) sender;
				// get the item in hand
				ItemStack itemInHand = player.getItemInHand();
				ItemMeta itemMeta = itemInHand.getItemMeta();

				// handle secondary command. eg: /lore <command>
				if (cmd1.equalsIgnoreCase("set")) {
					// parse the String[]
					String newLore = new String(args[0]);
					for (int i = 1; i < args.length; i++) {
						newLore = newLore + " " + args[i];
					}
					// edit the lore of the item in hand with the string
					// first create empty list, then add lore string
					ArrayList<String> newList = new ArrayList<String>(1);
					newList.add(newLore);
					// set lore
					itemMeta.setLore(newList);
					itemInHand.setItemMeta(itemMeta);
					EasyChat.sendMessage(player, "New lore set!");
					return true;
				}
				else if (cmd1.equalsIgnoreCase("replace")) {
					// requires line number to set
					try {
						int line = Integer.decode(args[0]);
						// parse the String[]
						String newLore = new String(args[1]);
						for (int i = 2; i < args.length; i++) {
							newLore = newLore + " " + args[i];
						}

						ArrayList<String> newList;
						if (itemMeta.hasLore()) {
							newList = (ArrayList<String>) itemMeta.getLore();
						} else {
							newList = new ArrayList<String>(1);
						}

						if (line > newList.size()) {
							// explain that its an invalid line
							EasyChat.sendError(player, "Invalid line number.");
						}
						else {
							// ok, replace the line
							newList.remove(line - 1);
							newList.add(line - 1, EasyLore.parseColors(newLore));
							itemMeta.setLore(newList);
							itemInHand.setItemMeta(itemMeta);
							EasyChat.sendMessage(sender, "New lore replaced!");
						}

					} catch (NumberFormatException e) {
						// explain that /lore replace requires line number
						EasyChat.sendError(sender, "/lore replace requires a line number!");
					}

					return true;
				}
				else if (cmd1.equalsIgnoreCase("add")) {
					// take current ItemMeta and add to the lore
					// parse the String[]
					String newLore = new String(args[0]);
					for (int i = 1; i < args.length; i++) {
						newLore = newLore + " " + args[i];
					}

					ArrayList<String> newList;
					if (itemMeta.hasLore()) {
						newList = (ArrayList<String>) itemMeta.getLore();
					} else {
						newList = new ArrayList<String>(1);
					}

					newList.add(EasyLore.parseColors(newLore));
					itemMeta.setLore(newList);
					itemInHand.setItemMeta(itemMeta);

					EasyChat.sendMessage(sender, "New lore added!");
					
					return true;
				}
				else if (cmd1.equalsIgnoreCase("remove")) {
					if (args.length == 1) {
						try {
							int line = Integer.decode(args[0]);

							ArrayList<String> newList;
							if (itemMeta.hasLore()) {
								newList = (ArrayList<String>) itemMeta.getLore();

								if (line > newList.size() || line < 1) {
									// explain that its an invalid line
									EasyChat.sendError(sender, "Invalid line number.");
								}
								else {
									// ok, replace the line
									newList.remove(line-1);
									itemMeta.setLore(newList);
									itemInHand.setItemMeta(itemMeta);
									EasyChat.sendMessage(sender, "Lore removed!");
								}
							} else {
								// explain that there needs to be lore to remove
								EasyChat.sendError(sender, "There needs to be lore to remove!");
							}


						} catch (NumberFormatException e) {
							// explain that /lore remove requires line number
							EasyChat.sendError(sender, "/lore remove requires a line number!");
						}
					} else {
						//explain that /lore remove takes only 1 argument
						EasyChat.sendError(sender, "/lore remove takes only 1 parameter!");
					}
					return true;
				}
				else if (cmd1.equalsIgnoreCase("insert")) {
					//insert lore at the specified index
					// requires line number to set
					try {
						int line = Integer.decode(args[0]);
						// parse the String[]
						String newLore = "";
						if (args.length > 1) {
							newLore = new String(args[1]);
						}
						for (int i = 2; i < args.length; i++) {
							newLore = newLore + " " + args[i];
						}

						ArrayList<String> newList;
						if (itemMeta.hasLore()) {
							newList = (ArrayList<String>) itemMeta.getLore();
						} else {
							newList = new ArrayList<String>(1);
						}

						if (line > newList.size() || line < 1) {
							// explain that its an invalid line
							EasyChat.sendError(sender, "Invalid line number.");
						}
						else {
							// ok, insert the line
							newList.add(line - 1, EasyLore.parseColors(newLore));
							itemMeta.setLore(newList);
							itemInHand.setItemMeta(itemMeta);
							EasyChat.sendMessage(sender, "New lore replaced!");
						}

					} catch (NumberFormatException e) {
						// explain that /lore replace requires line number
						EasyChat.sendError(sender, "/lore insert requires line number!");
					}

					return true;
				}
				else if (cmd1.equalsIgnoreCase("generate")) {
					//add a random lore for the specific item from a YAML file of prewritten lores

					return true;
				}
				else {
					//explain that the command after /lore was not recognized
					EasyChat.sendError(sender, "Command not recognized.");
					return true;
				}

			}
			// display a help menu for /lore here

			return true;
		} //If this has happened the function will return true. 
		else if(cmd.getName().equalsIgnoreCase("name")) {
			// /name renames items
			// get 1st argument
			String cmd1 = "";
			if (args.length == 1) cmd1 = args[0];

			if (!(sender instanceof Player)) {
				if (cmd1.equalsIgnoreCase("help")) {
					// display EasyLore /name help here
					displayHelp();
					return true;
				}
				EasyChat.sendError(sender, "This command can only be run by a player.");
				return true;
			} else {
				Player player = (Player) sender;
				// get the item in hand
				ItemStack itemInHand = player.getItemInHand();
				ItemMeta itemMeta = itemInHand.getItemMeta();

				// handle secondary command. eg: /name <command>
				if (cmd1.equalsIgnoreCase("default") && args.length == 1) {
					// TODO:set the name of the item to be the default
				} else {
					// name the item
					String newName = new String(args[0]);
					for (int i = 1; i < args.length; i++) {
						newName = newName + " " + args[i];
					}
					itemMeta.setDisplayName(newName);
					itemInHand.setItemMeta(itemMeta);
				}
				return true;
			}
		}
		// If this hasn't happened the a value of false will be returned.
		return false; 
	}
	
	private void displayHelp() {
		
	}
}