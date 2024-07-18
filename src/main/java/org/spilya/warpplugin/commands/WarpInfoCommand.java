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

public class WarpInfoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) return false;
        
        sender.sendMessage(
                Component.text(
                        "Команды:\n" + 
                                "warp - Получение списка всех варпа текущего игрока\n" +
                                "warp [Название] - Телепортация на выбранный варп\n" +
                                "setwarp [Название] - Создание варпа в текущей точке\n" +
                                "delwarp [Название] - Удаление выбранного варпа\n" +
                                "listwarp [Ник] - Получение списка всех варпа выбранного игрока\n" +
                                "delpwarps [Ник] - Удаление всех варпов выбранного игрока\n" +
                                "warpinfo - Получение текущего списка",
                        TextColor.color(181, 181, 181)
                ));
        return true;
    }
}
