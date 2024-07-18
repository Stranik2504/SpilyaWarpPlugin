package org.spilya.warpplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.spilya.warpplugin.commands.*;
import org.spilya.warpplugin.tabCompliters.EmptyTab;
import org.spilya.warpplugin.tabCompliters.OwnerTab;
import org.spilya.warpplugin.tabCompliters.WarpTab;

public final class SpilyaWarps extends JavaPlugin {

    @Override
    public void onEnable() {
        org.spilya.warpplugin.YAMLwarps.getConfig(); // Создание конфига с варпами
        this.getCommand("warp").setExecutor(new WarpCommand());
        this.getCommand("setwarp").setExecutor(new WarpSCommand());
        this.getCommand("delwarp").setExecutor(new WarpDCommand());
        this.getCommand("listwarp").setExecutor(new WarpLCommand());
        this.getCommand("delpwarps").setExecutor(new WarpDNCommand());

        this.getCommand("warp").setTabCompleter(new WarpTab());
        this.getCommand("setwarp").setTabCompleter(new EmptyTab());
        this.getCommand("delwarp").setTabCompleter(new WarpTab());
        this.getCommand("listwarp").setTabCompleter(new OwnerTab());
        this.getCommand("delpwarps").setTabCompleter(new OwnerTab());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
