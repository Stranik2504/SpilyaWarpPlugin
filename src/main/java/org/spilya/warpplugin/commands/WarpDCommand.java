package org.spilya.warpplugin.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.spilya.warpplugin.YAMLwarps;

public class WarpDCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            //Проверка что отправил игрок
            Player player = (Player) sender;

            if (player.hasPermission("spilyawarp.delete")) {
            //Если есть пермишен

                if (args.length == 1) {
                    //Проверка синтексиса
                    String warpName = args[0];
                    YamlConfiguration warpsFile = YAMLwarps.getConfig();

                    if (warpsFile.contains(warpName)) {
                        //Удаление варпа
                        warpsFile.set(warpName, null);
                        YAMLwarps.saveConfig(warpsFile);
                        player.sendMessage(Component.text("Варп " + warpName + " удалён", TextColor.color(6, 250, 50)));
                    } else {
                        //Варпа не существует
                        player.sendMessage(Component.text("Варп " + warpName + " не найден", TextColor.color(227, 39, 57)));
                    }
                    //Не верное количество компонентов
                } else {
                    player.sendMessage(Component.text("Изпользуй: /delwarp [Варп]", TextColor.color(227, 39, 57)));
                }
            } else {
                // Нет прав на команду
                player.sendMessage(Component.text("У вас нет прав что бы использовать данную команду", TextColor.color(227, 39, 57)));
            }

        }else{
            // Отправил не игрок
            sender.sendMessage(Component.text("Команду можно использовать только в игре", TextColor.color(227, 39, 57)));
        }
        return true;
    }
}