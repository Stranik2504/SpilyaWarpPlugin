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
        if (!(sender instanceof Player)) return new ArrayList<>();
        if (args.length != 1) return new ArrayList<>();

        YamlConfiguration warpFile = YAMLwarps.getConfig();
        ArrayList<String> playerList = new ArrayList<>();

        warpFile.getKeys(false).forEach(key -> {
            String warpOwner = warpFile.get(key + ".owner").toString();

            if (playerList.contains(warpOwner)) return;

            if (args[0].trim().isEmpty()) playerList.add(warpOwner);
            else if (warpOwner.contains(args[0])) playerList.add(warpOwner);
        });

        return playerList;
    }
}
