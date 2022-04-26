package net.runelite.client.plugins.xo.utils;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import org.pf4j.Extension;

import javax.inject.Singleton;

@Extension
@PluginDescriptor(
        name = "xoUtils",
        description = "Exo - Plugin Utilities"
)
@Slf4j
@SuppressWarnings("unused")
@Singleton
public class UtilsPlugin extends Plugin {

    @Provides
    UtilsConfiguration provideConfig(ConfigManager configManager) {
        return configManager.getConfig(UtilsConfiguration.class);
    }

}
