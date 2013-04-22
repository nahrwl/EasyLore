package com.github.techietotoro.EasyLore;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class EasyLore extends JavaPlugin {
	
	@Override
    public void onEnable(){
        // TODO Insert logic to be performed when the plugin is enabled
		// This will throw a NullPointerException if you don't have the command defined in your plugin.yml file!
		getCommand("lore").setExecutor(new EasyLoreExecutor(this));
		getCommand("name").setExecutor(new EasyLoreExecutor(this));
		
		// Setup EasyChat
		EasyChat.setPrefix(ChatColor.GRAY + "[EasyLore] ");
		EasyChat.setErrorPrefix(ChatColor.RED + "Error: ");
		EasyChat.setMessageColor(ChatColor.GREEN);
		EasyChat.setErrorColor(ChatColor.RED);
    }
 
    @Override
    public void onDisable() {
        // TODO Insert logic to be performed when the plugin is disabled
    }
    
    
    // Static helper methods //
    public static String parseColors(String str) {
    	String tempStr = str;
    	//tempStr.replaceAll("&0",ChatColor.BLACK);
    	return tempStr;
    }
    
}
