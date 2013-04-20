package com.github.techietotoro.EasyLore;

import org.bukkit.command.CommandSender;

public class EasyChat {
	
	private static int maxLines = 8;
	private static int maxChars = 50;
	
	private static String prefix = "";
	private static String suffix = "";
	
	private static String[] help;
	
	// Setters //
	
	public static void setPrefix(String str) {
		prefix = str;
	}
	
	public static void setSuffix(String str) {
		suffix = str;
	}

	// Public Methods //
	
	public static void sendMessage(CommandSender sender, String message) {
		sender.sendMessage(prefix + message + suffix);
	}
	
	public static void sendMessages(CommandSender sender, String[] messageArray) {
		for (int i = 0; i < messageArray.length; i++) {
			sender.sendMessage(prefix + messageArray[i] + suffix);
		}
	}
	
	public static void displayHelp(CommandSender sender) {
		// display help dialog here
	}
	
	
	// Private methods //
	
	private static void displayDialog(String[] strs) {
		
	}
	
	private static void wrapLines(String[] strs) {
		for (int i = 0; i < strs.length; i++) {
			String s = strs[i];
			if (s.length() > maxChars) {
				// need to move offending characters to new line
				String[] words = s.split("*. *.");
				String[] lines = new String[s.length() / maxChars];
				for (int j = 0; j < words.length; j++) {
					int charCount = 0;
					while (charCount < maxChars) {
						charCount += words[j].length();
					}
				}
			}
		}
	}
}
