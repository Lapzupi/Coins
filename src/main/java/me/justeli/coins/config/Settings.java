package me.justeli.coins.config;

import me.justeli.coins.Coins;
import me.justeli.coins.config.api.RegisterConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * Created by Eli on 12/14/2016.
 * Rewritten by Eli on July 9, 2021.
 */
public class Settings
{
    public static void reload ()
    {
        for (String language : new String[]{"english", "dutch", "spanish", "german", "french", "swedish", "chinese", "hungarian"})
        {
            if (!new File(Coins.plugin().getDataFolder() + File.separator + "language" + File.separator + language + ".json").exists())
            {
                Coins.plugin().saveResource("language/" + language + ".json", false);
                Coins.console(Level.INFO, "Added the language '" + language + "' to Coins, which can now be used in the config.");
            }
        }

        Config.resetWarningCount();

        RegisterConfig.parse();
        Message.initialize(Config.LANGUAGE);
    }

    public static List<String> get ()
    {
        List<String> items = new ArrayList<>();

        for (Map.Entry<String, Object> part : RegisterConfig.keys().entrySet())
        {
            items.add(part.getKey() + "\u00BB " + part.getValue());
        }

        return items;
    }
}