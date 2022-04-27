package net.runelite.client.plugins.xo.utils;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.MenuAction;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.xo.utils.models.LegacyMenuEntry;
import org.pf4j.Extension;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Extension
@PluginDescriptor(
        name = "xoUtils",
        description = "Exo - Plugin Utilities"
)
@Slf4j
@SuppressWarnings("unused")
@Singleton
public class UtilsPlugin extends Plugin {

    @Inject
    private Container container;

    @Provides
    UtilsConfiguration provideConfig(ConfigManager configManager) {
        return configManager.getConfig(UtilsConfiguration.class);
    }

    @Subscribe
    private void onMenuOptionClicked(MenuOptionClicked event) {
        Optional<LegacyMenuEntry> oMenuEntry = container.get(UtilsPlugin.class, LegacyMenuEntry.class);
        if (oMenuEntry.isPresent()) {
            LegacyMenuEntry menuEntry = oMenuEntry.get();

            event.setMenuOption(menuEntry.getOption());
            event.setMenuTarget(menuEntry.getTarget());
            event.setId(menuEntry.getIdentifier());
            event.setMenuAction(MenuAction.of(menuEntry.getMenuAction()));
            event.setParam0(menuEntry.getParam0());
            event.setParam1(menuEntry.getParam1());

            container.remove(UtilsPlugin.class, LegacyMenuEntry.class);
        }
    }

}