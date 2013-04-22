package com.github.techietotoro.EasyLore;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class EasyChat {
	
	private static int maxLines = 8;
	private static int maxChars = 50;
	
	private static String prefix = "";
	private static String suffix = "";
	
	private static String errorPrefix = "";
	private static String errorSuffix = "";
	
	private static ChatColor messageColor = ChatColor.GREEN;
	private static ChatColor errorColor = ChatColor.RED;
	
	private static String[][] help;
	
	// Setters //
	
	public static void setMaxLines(int max) {
		maxLines = max;
	}
	
	public static void setMaxCharsPerLine(int max) {
		maxChars = max;
	}
	
	public static void setPrefix(String str) {
		prefix = str;
	}
	
	public static void setSuffix(String str) {
		suffix = str;
	}
	
	public static void setErrorPrefix(String str) {
		errorPrefix = str;
	}
	
	public static void setErrorSuffix(String str) {
		errorSuffix = str;
	}
	
	public static void setMessageColor(ChatColor color) {
		messageColor = color;
	}
	
	public static void setErrorColor(ChatColor color) {
		errorColor = color;
	}
	
	public static void setHelp(String[] theHelp) {
		help = paginate(theHelp);
	}

	// Public Methods //
	
	public static void sendMessage(CommandSender sender, String message) {
		sender.sendMessage(prefix + messageColor + message + ChatColor.RESET + suffix);
	}
	
	public static void sendMessages(CommandSender sender, String[] messageArray) {
		for (int i = 0; i < messageArray.length; i++) {
			sender.sendMessage(prefix + messageColor + messageArray[i] + ChatColor.RESET + suffix);
		}
	}
	
	public static void sendError(CommandSender sender, String message) {
		sender.sendMessage(prefix + errorPrefix + errorColor + message + ChatColor.RESET + errorSuffix + suffix);
	}
	
	public static void displayHelpPage(CommandSender sender, int page) {
		// display help dialog here
		displayDialog(sender, page, help);
	}
	
	public static void displayHelpItem(CommandSender sender, int item) {
		
	}
	
	
	// Private methods //
	
	private static void displayDialog(CommandSender sender, int page, String[][] strs) {
		String[] newStrs = strs[page];
		sendMessages(sender, newStrs);
	}
	
	private static String[][] paginate(String[] strs) {
		String[] newStrs = wrapLines(strs);
		String[][] result = new String[newStrs.length / maxLines][maxLines];
		for (int i = 0; i < newStrs.length; i++) {
			result[i / maxLines][i % maxLines] = newStrs[i];
		}
		return result;
	}
	
	
	// COMPLETELY UNTESTED!
	private static String[] wrapLines(String[] strs) {
		ArrayList<String> tempStrs = new ArrayList<String>(Arrays.asList(strs));
		for (int i = 0; i < strs.length; i++) {
			String s = strs[i];
			if (s.length() > maxChars) {
				// need to move offending characters to new line
				String[] words = s.split("*. *.");
				String[] lines = new String[s.length() / maxChars];
				int k = 0;
				for (int j = 0; j < lines.length; j++) {
					int charCount = 0;
					while (charCount < maxChars) {
						charCount += words[k].length();
						lines[j] = lines[j] + " " + words[k];
						k++;
					}
				}
				for (int j = lines.length; j >= 0; j--) { // add wrapped lines back to arraylist
					tempStrs.add(i, lines[j]);
				}
			}
		}
		return (String[]) tempStrs.toArray();
	}
}
