package org.spilya.warpplugin.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.spilya.warpplugin.YAMLwarps;

public class WarpSCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        //warps <варп>
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("spilyawarp.set") || sender.isOp()) {
                if (args.length == 1) {
                    YamlConfiguration warpsFile = YAMLwarps.getConfig();
                    String warpName = args[0];
                    // Добовление варпа !!!! проверка на то что варп уже существует
                    if (!warpsFile.contains(warpName)) {
                        String warpOwner = player.getName();
                        String worldName = player.getLocation().getWorld().getName();
                        double x = player.getLocation().getX();
                        double y = player.getLocation().getY();
                        double z = player.getLocation().getZ();
                        float yaw = player.getYaw();
                        float pitch = player.getPitch();

                        warpsFile.set(warpName + ".owner", warpOwner);
                        warpsFile.set(warpName + ".world", worldName);
                        warpsFile.set(warpName + ".x", x);
                        warpsFile.set(warpName + ".y", y);
                        warpsFile.set(warpName + ".z", z);
                        warpsFile.set(warpName + ".yaw", yaw);
                        warpsFile.set(warpName + ".pitch", pitch);
                        YAMLwarps.saveConfig(warpsFile);
                        Component succesComp = Component.text("Варп " + warpName + " успешно создан ", TextColor.color(6, 250, 50));
                        player.sendMessage(succesComp);
                    }else{
                        Component alreadyExistComp = Component.text("Варп " + args[0] + " уже существует ", TextColor.color(227, 39, 57));
                        Component alreadyExistTPComp = Component.text("[ТП]", TextColor.color(8, 224, 252), TextDecoration.UNDERLINED).clickEvent(ClickEvent.runCommand("/warp " + warpName));
                        if (player.hasPermission("spilyawarp.teleport")){
                            player.sendMessage(alreadyExistComp.append(alreadyExistTPComp));
                        }else {
                            player.sendMessage(alreadyExistComp);
                        }
                    }
                } else {
                    Component syntaxError = Component.text("Используй: /warps [Варп]", TextColor.color(227, 39, 57));
                    player.sendMessage(syntaxError);
                }
            }else {
                Component permissonError = Component.text("У вас нет прав что бы использовать данную команду", TextColor.color(227, 39, 57));
                player.sendMessage(permissonError);
            }


        }else {
            Component senderNotPlayer = Component.text("Команду можно использовать только в игре", TextColor.color(227, 39, 57));
            sender.sendMessage(senderNotPlayer);
        }
        return true;
    }
}