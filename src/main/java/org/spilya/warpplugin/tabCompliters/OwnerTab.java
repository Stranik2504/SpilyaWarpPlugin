package org.spilya.warpplugin.tabCompliters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.spilya.warpplugin.YAMLwarps;

import java.util.ArrayList;
import java.util.List;

public class OwnerTab implements TabCompleter {


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        List<String> emtpyList = new ArrayList<>();

        if (sender instanceof Player){
            YamlConfiguration warpFile = YAMLwarps.getConfig();

            if (args.length == 1){
                ArrayList<String> playerList = new ArrayList<>();
                warpFile.getKeys(false).forEach(key -> {
                    String warpOwner = warpFile.get(key + ".owner").toString();
                    if (!playerList.contains(warpOwner)){
                        playerList.add(warpOwner);
                    }
                });
                return playerList;

            }else {
                return emtpyList;
            }





        }else {
            return emtpyList;

        }



    }
}
