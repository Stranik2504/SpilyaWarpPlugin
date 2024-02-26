package org.spilya.warpplugin.tabCompliters;

import com.sun.java.swing.ui.SplashScreen;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spilya.warpplugin.YAMLwarps;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WarpTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        List<String> emptyList = new ArrayList<>();
        if (sender instanceof Player) {
            //Проверка на игрока
            YamlConfiguration warpsFile = YAMLwarps.getConfig();



            if (args.length == 1){
                //    /warp       <warp>
                //   (command)   (arg[0])
                List<String> finalWarpList = new ArrayList<>();
                warpsFile.getKeys(false).forEach(key -> finalWarpList.add(key));
                return finalWarpList;
            } else {
                return emptyList;
            }

        }else {
            return emptyList;

        }
    }
}
