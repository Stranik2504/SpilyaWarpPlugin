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

public class WarpLCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            //првоерка на игрока

            Player player = (Player) sender;
            if (args.length == 1) {
                //Првоерка на синтекс

                YamlConfiguration warpFile = YAMLwarps.getConfig();
                String target = args[0];
                ArrayList<String> finalList = new ArrayList<>();

                //Берём только варпы этого игрока
                warpFile.getKeys(false).stream().forEach(key -> {
                    String warpOwner = warpFile.get(key + ".owner").toString();
                    if (warpOwner.equals(target)) {
                        finalList.add(key);
                    }
                });

                if (!finalList.isEmpty()) {
                    //Провкрка что варпы есть

                    player.sendMessage(Component.text("Варпы игрока " + target + ":", TextColor.color(181, 181, 181)));
                    if (player.hasPermission("spilyawarp.teleport")) {
                        //Если есть пермишен на телепорт то можно телепортироваться по клику в чате
                        finalList.stream().forEach(warp -> player.sendMessage(Component.text(warp, TextColor.color(181, 181, 181))
                                .clickEvent(ClickEvent.runCommand("/warp " + warp))));
                    } else {
                        //Низя кликать потому что нету прав
                        finalList.stream().forEach(warp -> player.sendMessage(Component.text(warp, TextColor.color(181, 181, 181))));
                    }
                } else {
                    //Нет доступных варпов
                    player.sendMessage(Component.text("У игрока " + target + " нет варпов", TextColor.color(181, 181, 181)));
                }

            }else {
                // Ошибка синтексиса
                player.sendMessage(Component.text("Используй: /listwarp [Ник]", TextColor.color(227, 39, 57)));
            }
        }else {
            //Только в игре можно использовать
            sender.sendMessage(Component.text("Команду можно использовать только в игре", TextColor.color(227, 39, 57)));
        }

        return true;
    }
}
