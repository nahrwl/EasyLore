package com.github.techietotoro.EasyLore;

import org.bukkit.plugin.java.JavaPlugin;

public final class EasyLore extends JavaPlugin {
	
	@Override
    public void onEnable(){
        // TODO Insert logic to be performed when the plugin is enabled
		// This will throw a NullPointerException if you don't have the command defined in your plugin.yml file!
		getCommand("lore").setExecutor(new EasyLoreExecutor(this));
    }
 
    @Override
    public void onDisable() {
        // TODO Insert logic to be performed when the plugin is disabled
    }
    
}
