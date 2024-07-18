package org.spilya.warpplugin.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
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
                        YamlConfiguration warpsFile = YAMLwarps.getConfig();
                        
                        //Если есть пермишен на телепорт то можно телепортироваться по клику в чате
                        finalList.stream().forEach(warp -> {
                            String nameWorld = Bukkit.getWorld(warpsFile.get(warp + ".world").toString()).getName();
                            double x = Double.parseDouble(warpsFile.get(warp + ".x").toString());
                            double y = Double.parseDouble(warpsFile.get(warp + ".y").toString());
                            double z = Double.parseDouble(warpsFile.get(warp + ".z").toString());
                            TextColor color;
                            String name;

                            switch (nameWorld) {
                                case "world":
                                    // Overworld
                                    color = TextColor.color(86, 255, 85);
                                    name = "Overworld";
                                    break;
                                case "world_nether":
                                    // Nether
                                    color = TextColor.color(255, 85, 85);
                                    name = "Nether";
                                    break;
                                case "world_the_end":
                                    // End
                                    color = TextColor.color(255, 84, 255);
                                    name = "End";
                                    break;
                                default:
                                    color = TextColor.color(181, 181, 181);
                                    name = "Else";
                                    break;
                            }

                            player.sendMessage(
                                    Component
                                            .text(warp, color)
                                            .clickEvent(ClickEvent.runCommand("/warp " + warp))
                                            .hoverEvent(HoverEvent.showText(
                                                    Component
                                                            .text("Телепортироваться (Корды: " + (int)x + " " + (int)y + " " + (int)z + "; Мир: " + name + ")", color)
                                            ))
                            );
                        });
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
