package org.spilya.warpplugin.tabCompliters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.spilya.warpplugin.YAMLwarps;

import java.util.ArrayList;
import java.util.List;

public class WarpTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) return new ArrayList<>();
        if (args.length != 1) return new ArrayList<>();

        //Проверка на игрока
        YamlConfiguration warpsFile = YAMLwarps.getConfig();
        List<String> finalWarpList = new ArrayList<>();
        
        warpsFile.getKeys(false).forEach(key -> {
            if (args[0].trim().isEmpty()) finalWarpList.add(key);
            else if (key.contains(args[0])) finalWarpList.add(key);
        });
        return finalWarpList;
    }
}
