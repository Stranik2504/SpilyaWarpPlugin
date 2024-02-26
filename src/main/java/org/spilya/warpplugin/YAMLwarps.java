package org.spilya.warpplugin;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class YAMLwarps {
    public static YamlConfiguration getConfig(){
        File ConfigFile = new File("plugins"+ File.separator + "SpilyaWarps" + File.separator + "warps.yml");
        if (!ConfigFile.exists()){
            try {
                ConfigFile.createNewFile();
            } catch (IOException e) {}
            YamlConfiguration config = YamlConfiguration.loadConfiguration(ConfigFile);
            try {
                config.save("plugins"+ File.separator + "SpilyaWarps" + File.separator + "warps.yml");
            } catch (IOException e){}
            return config;


        }else {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(ConfigFile);
            return config;
        }
    }





    public static void saveConfig(YamlConfiguration configYAML){
        try {
            configYAML.save("plugins"+ File.separator + "SpilyaWarps" + File.separator + "warps.yml");
        } catch (IOException e) {}


    }



}
