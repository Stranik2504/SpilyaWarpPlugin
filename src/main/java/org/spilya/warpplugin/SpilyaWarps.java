package org.spilya.warpplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.spilya.warpplugin.commands.*;
import org.spilya.warpplugin.tabCompliters.EmptyTab;
import org.spilya.warpplugin.tabCompliters.OwnerTab;
import org.spilya.warpplugin.tabCompliters.WarpTab;

public final class SpilyaWarps extends JavaPlugin {

    @Override
    public void onEnable() {
        YAMLwarps.getConfig(); // Создание конфига с варпами
        this.getCommand("warp").setExecutor(new WarpCommand());
        this.getCommand("warps").setExecutor(new WarpSCommand());
        this.getCommand("warpd").setExecutor(new WarpDCommand());
        this.getCommand("warpdn").setExecutor(new WarpDNCommand());
        this.getCommand("warpl").setExecutor(new WarpLCommand());

        this.getCommand("warp").setTabCompleter(new WarpTab());
        this.getCommand("warps").setTabCompleter(new EmptyTab());
        this.getCommand("warpd").setTabCompleter(new WarpTab());
        this.getCommand("warpdn").setTabCompleter(new OwnerTab());
        this.getCommand("warpl").setTabCompleter(new OwnerTab());




    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
