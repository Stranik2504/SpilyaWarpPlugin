package org.spilya.warpplugin.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.spilya.warpplugin.YAMLwarps;

import java.util.Set;

public class WarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender,Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            // /warp - Список варпов
            // /warp <warp> - телепорт на варп
                YamlConfiguration warpsFile = YAMLwarps.getConfig();
                //вывод варпов
                if (args.length == 0) {
                    Set<String> warpsList = warpsFile.getKeys(false);
                    if (warpsList.size() != 0) {
                        player.sendMessage(Component.text("Варпы:", TextColor.color(181, 181, 181)));

                        if (player.hasPermission("spilyawarp.teleport")) {
                            warpsList.stream().forEach(warp -> {
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
                        }else {
                            warpsList.stream().forEach(warp -> player.sendMessage(
                                    Component
                                        .text(warp, TextColor.color(181, 181, 181))
                            ));
                        }
                    } else {
                        player.sendMessage(Component.text("Нет доступных варпов", TextColor.color(181, 181, 181)));
                    }
                // Телепорт на варп
                } else if (args.length == 1) {
                    String warpName = args[0];
                    if (player.hasPermission("spilyawarp.teleport")) {
                        if (warpsFile.contains(warpName)) {
                            World world = Bukkit.getWorld(warpsFile.get(warpName + ".world").toString());
                            double x = Double.valueOf(warpsFile.get(warpName + ".x").toString());
                            double y = Double.valueOf(warpsFile.get(warpName + ".y").toString());
                            double z = Double.valueOf(warpsFile.get(warpName + ".z").toString());
                            Float yaw = Float.valueOf(warpsFile.get(warpName + ".yaw").toString());
                            Float pitch = Float.valueOf(warpsFile.get(warpName + ".pitch").toString());
                            Location teleportLocation = new Location(world, x, y, z, yaw, pitch);
                            player.teleport(teleportLocation);
                            player.sendMessage(Component.text("Вы телепортированны на варп " + warpName, TextColor.color(181, 181, 181)));
                        }else {
                            player.sendMessage(Component.text("Варпа не существует", TextColor.color(227, 39, 57)));
                        }
                    }else {
                        player.sendMessage(Component.text("У вас нет прав на использование данной команды", TextColor.color(227, 39, 57)));
                    }
                //слишком много аргументов
                } else {
                    player.sendMessage(Component.text("Используй: /warp [Варп]", TextColor.color(227, 39, 57)));
                }
            }else {
            //Оправитель не игрок
            sender.sendMessage(Component.text("Команду можно использовать только в игре", TextColor.color(227, 39, 57)));
        }
        return true;
    }
}
