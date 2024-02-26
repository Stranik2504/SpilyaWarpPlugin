package org.spilya.warpplugin.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.spilya.warpplugin.YAMLwarps;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class WarpDNCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        YamlConfiguration warpFile = YAMLwarps.getConfig();
        // warpdn [Ник] - удаляет все варпы этого игрока
        if (sender instanceof Player) {
            //Првоерка что команду отправил игрок
                Player player = (Player) sender;
                if (player.hasPermission("spilyawarp.delete")) {
                    //проверка пермишена
                    if (args.length == 1) {
                    //Првоерка синтекса
                        String target = args[0];
                        List<String> targetsWarps = new ArrayList<>();
                        warpFile.getKeys(false).stream().forEach(key -> {
                            String warpOwner = warpFile.get(key + ".owner").toString();
                            if (warpOwner.equalsIgnoreCase(target)) {
                                targetsWarps.add(key);
                            }
                        });
                        if (!targetsWarps.isEmpty()) {
                            //Если у игрока есть варпы
                            targetsWarps.stream().forEach(key -> warpFile.set(key, null));
                            YAMLwarps.saveConfig(warpFile);
                            player.sendMessage(Component.text("Все варпы игрока " + target + " удаленны", TextColor.color(181, 181, 181)));
                        }else {
                            player.sendMessage(Component.text("У игрока " + target + " нет варпов", TextColor.color(181, 181, 181)));
                        }

                    }else {
                        // не верный ситексис
                        player.sendMessage(Component.text("Испльзуй: /warpdn [Ник]", TextColor.color(227, 39, 57)));;

                    }
                } else {
                    player.sendMessage(Component.text("У вас нет прав что бы использовать данную команду", TextColor.color(227, 39, 57)));
                }
        }else {
            sender.sendMessage(Component.text("Команду можно использовать только в игре", TextColor.color(227, 39, 57)));
        }
        return true;
    }
}
