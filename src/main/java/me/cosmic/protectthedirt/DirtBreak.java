package me.cosmic.protectthedirt;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

public class DirtBreak implements Listener {
    ProtectTheDirt plugin;

    public DirtBreak(ProtectTheDirt plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (e.getBlock().getType().equals(Material.DIRT)) {
            Player p = e.getPlayer();
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            String formattedDate = myDateObj.format(myFormatObj);
            System.out.println("[" + formattedDate + "]" + " DIRT HAS BEEN BROKEN BY " + p.getName());
            logToFile("[" + formattedDate + "]" + " DIRT HAS BEEN BROKEN BY " + p.getName());
        }
    }
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if (event.getEntity() instanceof Creeper) {
            for (Block block : event.blockList().toArray(new Block[event.blockList().size()])){
                if(block.getType() == Material.DIRT){
                    Player[] p = Bukkit.getOnlinePlayers().toArray(new Player[0]);
                    LocalDateTime myDateObj = LocalDateTime.now();
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

                    String formattedDate = myDateObj.format(myFormatObj);
                    System.out.println("[" + formattedDate + "]" + " DIRT HAS BEEN BROKEN BY " + Arrays.toString(p));
                    logToFile("[" + formattedDate + "]" + " DIRT HAS BEEN BROKEN BY " + Arrays.toString(p));

                }
            }
        }
    }

    public void logToFile(String message) {

        try {
            File dataFolder = plugin.getDataFolder();
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }

            File saveTo = new File(plugin.getDataFolder(), "dirtBroken.txt");
            if (!saveTo.exists()) {
                saveTo.createNewFile();
            }


            FileWriter fw = new FileWriter(saveTo, true);

            PrintWriter pw = new PrintWriter(fw);

            pw.println(message);

            pw.flush();

            pw.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}
