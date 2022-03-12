package me.cosmic.protectthedirt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class Logging {
    private ProtectTheDirt plugin = ProtectTheDirt.getPlugin(ProtectTheDirt.class); // Get instance of main class, you dont need this if you are putting this in your main class
    public File logsfolder; // Create File

    public void setuplogfolder() {
        if (!plugin.getDataFolder().exists()) { // Check if plugin folder exists
            plugin.getDataFolder().mkdir(); // if not then create it
        }

        logsfolder = new File(plugin.getDataFolder(), "logs"); // Set the path of the new logs folder

        if (!logsfolder.exists()) { // Check if logs folder exists
            logsfolder.mkdirs(); // if not then create it
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Created the logs folder"); // Send a message to console that the folder has been created
        }
    }
    public void logToFile(String file, String message) {
        try {
            File dataFolder = plugin.getDataFolder(); // Sets file to the plugins/<pluginname> folder
            if (!dataFolder.exists()) { // Check if logs folder exists
                dataFolder.mkdir(); // if not then create it
            }
            File saveTo = new File(plugin.getDataFolder() + "/logs/", file + ".log"); // Sets the path of the new log file
            if (!saveTo.exists()) { // Check if logs folder exists
                saveTo.createNewFile(); // if not then create it
            }
            FileWriter fw = new FileWriter(saveTo, true); // Create a FileWriter to edit the file
            PrintWriter pw = new PrintWriter(fw); // Create a PrintWriter
            pw.println(message); // This is the text/message you will be writing to the file
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace(); // If theres any errors in this process it will print the error in console
        }
    }
}
