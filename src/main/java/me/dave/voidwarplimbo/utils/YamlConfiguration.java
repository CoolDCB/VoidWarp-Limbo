package me.dave.voidwarplimbo.utils;

import me.dave.voidwarplimbo.VoidwarpLimbo;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.util.Map;

public class YamlConfiguration {
    private final VoidwarpLimbo plugin = VoidwarpLimbo.getInstance();
    private Map<String, Object> configMap;

    public YamlConfiguration() {
        plugin.getDataFolder().mkdirs();
    }

    public String getString(String path, String def) {
        Object obj = configMap.get(path);
        if (obj == null) return def;
        return (String) obj;
    }

    public double getDouble(String path, double def) {
        Object obj = configMap.get(path);
        if (obj instanceof Integer) {
            int integer = (int) obj;
            obj = (double) integer;
        }
        if (obj == null) return def;
        return (double) obj;
    }

    public void reloadConfig() {
        Yaml yaml = new Yaml();
        FileInputStream configStream = null;

        try {
            configStream = new FileInputStream(plugin.getDataFolder().toPath() + "/config.yml");
        } catch (FileNotFoundException err) {
            err.printStackTrace();
        }

        configMap = yaml.load(configStream);
    }

    public File saveDefaultConfig() {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            ClassLoader loader = this.getClass().getClassLoader();
            InputStream defaultConfig = loader.getResourceAsStream("config.yml");

            if (defaultConfig != null) {
                try {
                    Files.copy(defaultConfig, configFile.toPath());
                } catch (IOException err) {
                    throw new RuntimeException(err);
                }
            }
        }

        reloadConfig();
        return configFile;
    }
}
